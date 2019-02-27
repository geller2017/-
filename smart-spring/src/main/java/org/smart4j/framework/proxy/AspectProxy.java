package org.smart4j.framework.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 
 * @author Administrator 切面代理
 * 需要注意的是AspectProxy类中的doProxy方法，我们从proxyChain参数中获取了目标类、
 * 目标方法和方法参数，随后通过一个try...catch...finally代码块来实现调用框架，从架构中抽象
 * 出一系列的“钩子方法”，这些抽象方法可在AspectProxy的子类中有选择性地进行实现
 */
public class AspectProxy implements Proxy {
	private static final Logger logger = LoggerFactory
			.getLogger(AspectProxy.class);

	@Override
	public Object doProxy(ProxyChain proxy) {
		Object result = null;
		Class<?> clazz = proxy.getTargetClass();
		Method method = proxy.getTargetMethod();
		Object[] args = proxy.getMethodParams();

		begin();
		try {
			if (intecep(clazz, method, args)) {
				before(clazz, method, args);
				result = proxy.doProxyChain();
				after(clazz, method, args);
			}else{
				result = proxy.doProxyChain();
			}
		} catch (Throwable e) {
			error(clazz, method, args);
		} finally{
			end();
		}
		return result;
	}

	public void begin() {
	}

	public boolean intecep(Class<?> clazz, Method method, Object[] args) {
		return true;
	}

	public void before(Class<?> clazz, Method method, Object[] args) {
	}

	public void after(Class<?> clazz, Method method, Object[] args) {
	}

	public void error(Class<?> clazz, Method method, Object[] args) {
	}

	public void end() {
	}

}
