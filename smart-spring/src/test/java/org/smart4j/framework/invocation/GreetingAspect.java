/*package org.smart4j.framework.invocation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class GreetingAspect {
	
	@Around("execution(* org.smart4j.framework.invocation.*.*(..))")
	public Object aroud(ProceedingJoinPoint point) throws Throwable{
		System.out.println("====aroud before");
		Object res = point.proceed();
		System.out.println(point.getSignature().getName());
		System.out.println("====aroud after");
		return res;
	}
	
	@Before("execution(* org.smart4j.framework.invocation.HelloImpl.*(..))")
	public void before(){
		System.out.println("====Before");
	}
	
	@After("execution(* org.smart4j.framework.invocation.HelloImpl.*(..))")
	public void after(){
		System.out.println("====After");
	}
	
	@AfterReturning("execution(* org.smart4j.framework.invocation.HelloImpl.*(..))")
	public void afterReturning(){
		System.out.println("====AfterReturning");
	}
}
*/