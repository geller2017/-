package org.smart4j.framework.invocation;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

//编写一个重要的拦截器，来实现引入增强。
//在不改变HelloImpl类的情况下，让其实现apology接口的功能
public class IntruduceIntercepter implements MethodInterceptor,Apology{
	private Object delegate;
	
	public IntruduceIntercepter(){
		this.delegate = this;
	}
	
	public IntruduceIntercepter(Object delegate){
		this.delegate = delegate;
	}
	
	
	@Override
	public void saySorry(String word) {
		System.out.println("sorry!"+word);
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		Class<?> clazz = method.getDeclaringClass();
		if(clazz.isAssignableFrom(Apology.class)){
			return method.invoke(delegate, args);
		}
		return proxy.invokeSuper(obj, args);
	}
	
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(HelloImpl.class);
		enhancer.setInterfaces(new Class[]{Apology.class});
		enhancer.setCallback(new IntruduceIntercepter());
		
		HelloImpl hello = (HelloImpl) enhancer.create();
		hello.say("geller");
		
		Apology apology = (Apology) hello;
		apology.saySorry("geller");
		
	}
	

}
