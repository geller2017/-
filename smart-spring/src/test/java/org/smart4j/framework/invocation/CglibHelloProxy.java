package org.smart4j.framework.invocation;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibHelloProxy implements MethodInterceptor{
	
	private static CglibHelloProxy CGLIB_PROXY = new CglibHelloProxy();
	private CglibHelloProxy() {}
	
	public static CglibHelloProxy getInstance(){
		return CGLIB_PROXY;
	}
	
	@Override
	public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		before();
		proxy.invokeSuper(target, args);
		after();
		return null;
	}
	
	private void before(){
		System.out.println("before");
	}
	
	private void after(){
		System.out.println("after");
	}
	
	
	public <T> T getProxy(Class<T> clz){
		return (T) Enhancer.create(clz, this);
	}
	
	
	public static void main(String[] args) {
		HelloImpl hello = CglibHelloProxy.getInstance().getProxy(HelloImpl.class);
		hello.say("geller");
	}
	
	
}
