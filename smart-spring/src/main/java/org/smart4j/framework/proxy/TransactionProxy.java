package org.smart4j.framework.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.helper.DatabaseHelper;

/***
 * 事务代理切面类，让它实现Proxy接口，在doProxy方法中完成事务控制的相关逻辑
 * 定义一个FLAG_HOLDER的本地线程变量，它是一个标志
 * 可以保证同一个线程中事务控制相关逻辑只会执行一次。
 * 通过ProxyChain 对象可获取目标方法
 * 进而判断该方法是否带有Transaction注解。
 * 首先调用DatabaseHelper.beginTransaction方法卡其事务
 * 然后调用ProxyChan对象的doProxyChain方法执行目标方法
 * 接着调用DatabaseHeloer.commitTransaction方法回滚事务
 * 最后别忘了移除FLAG_HOLDER本地线程变量中的标志 
 * 
 *
 */
public class TransactionProxy implements Proxy{
	//数据是否自动提交
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);
	private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>(){
		protected Boolean initialValue() {
			return false;
		}
	};
	
	
	@Override
	public Object doProxy(ProxyChain proxyChain) {
		Object result = null;
		boolean flag = FLAG_HOLDER.get();
		Method method = proxyChain.getTargetMethod();
		if(!flag && method.isAnnotationPresent(Transaction.class)){
			FLAG_HOLDER.set(true);
			try{
				DatabaseHelper.beginTransaction();
				LOGGER.debug("begin transaction");
				
				result = proxyChain.doProxyChain();
				DatabaseHelper.connitTransaction();
				
				LOGGER.debug("commit transaction");
			}catch (Throwable e) {
				DatabaseHelper.rollbackTransaction();
				LOGGER.debug("rollback transaction");
			}finally{
				FLAG_HOLDER.remove();
			}
		}else{
			try {
				result = proxyChain.doProxyChain();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
}
