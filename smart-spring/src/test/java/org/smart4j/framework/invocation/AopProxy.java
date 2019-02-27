package org.smart4j.framework.invocation;

import org.springframework.aop.framework.ProxyFactory;

public class AopProxy {
	
	public static void main(String[] args) {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new HelloImpl());
		/*proxyFactory.addAdvice(new HelloAfterAdvice());
		proxyFactory.addAdvice(new HelloBeforeAdvice());*/
		proxyFactory.addAdvice(new InterceptorProxy());
		HelloImpl helloImpl = (HelloImpl) proxyFactory.getProxy();
		helloImpl.say("geller");
	}
	
}
