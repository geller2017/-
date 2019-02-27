package org.smart4j.framework.invocation;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;
@Component
public class ErrorAdvice implements ThrowsAdvice{
	public void afterThrowing( Method method, Object args, Object target, Throwable throwable ){
		System.out.println("-----Throw exception------");
		System.out.println("Target class:"+target.getClass().getName());
		System.out.println("Method name:"+method.getName());
		System.out.println("Exception Message:"+throwable.getMessage());
		System.out.println("--------------------------------");
	};
}
