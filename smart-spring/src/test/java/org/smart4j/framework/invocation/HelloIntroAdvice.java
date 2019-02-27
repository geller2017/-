package org.smart4j.framework.invocation;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;
@Component
public class HelloIntroAdvice extends DelegatingIntroductionInterceptor implements Apology{

	@Override
	public void saySorry(String word) {
		System.out.println("Sorry!"+word);
	}
	
	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		return super.invoke(mi);
	}

}
