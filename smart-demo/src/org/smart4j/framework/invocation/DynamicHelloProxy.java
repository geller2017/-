package org.smart4j.framework.invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicHelloProxy implements InvocationHandler {
	Object target;

	public DynamicHelloProxy(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result;
		before();
		result = method.invoke(target, args);
		after();
		return result;
	}

	private void before() {
		System.out.println("before");
	}

	private void after() {
		System.out.println("after");
	}

	public <T> T getProxy(){
		return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), this);
	}
	
	public static void main(String[] args) {
		Hello hello = new HelloImpl();
		DynamicHelloProxy proxy = new DynamicHelloProxy(hello);
		Hello helloProxy = proxy.getProxy();
		helloProxy.say("geller");
	}
}
