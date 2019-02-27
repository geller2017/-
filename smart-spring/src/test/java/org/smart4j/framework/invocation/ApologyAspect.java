/*package org.smart4j.framework.invocation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApologyAspect {
	
	@Around("@annotation(org.smart4j.framework.invocation.Tag)")
	public void aroud(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("ApologyAspect::aroud::before");
		pjp.proceed();
		System.out.println("ApologyAspect::aroud::after");
	}
}
*/