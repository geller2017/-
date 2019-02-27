package org.smart4j.chapter4.aspect;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.proxy.AspectProxy;

/***
 * 
 * @author Administrator
 * 拦截所有Controller方法
 * 我们只需要实现before和after方法，就可以在目标方法执行前后添加其他需要执行的代码了
 * 我们还需要在整个框架里使用ProxyManager来创建代理对象，并对该代理对象放入框架底层的beanMap中
 * 随后才能通过IOC将被代理的对象注入到其他对象中
 */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy{
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
	//记录时间
	private Long begin;
	//重写before方法
	@Override
	public void before(Class<?> clazz, Method method, Object[] args) {
		begin = System.currentTimeMillis();
		LOGGER.debug("------begin------");
		LOGGER.debug(String.format("class: %s", clazz.getName()));
		LOGGER.debug(String.format("method: %s", method.getName()));
	}
	
	@Override
	public void after(Class<?> clazz, Method method, Object[] args) {
		LOGGER.debug(String.format("time: %dms", System.currentTimeMillis()-begin));
		LOGGER.debug("------end------");
	}
}
