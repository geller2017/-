package org.smart4j.framework.invocation;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class HelloBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		System.out.println("before");
	}

}
