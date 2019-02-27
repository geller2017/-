package org.smart4j.framework.invocation;

import org.springframework.stereotype.Component;

@Component
public class StaticHelloProxy implements Hello{
	Hello hello;
	public StaticHelloProxy() {
		hello = new HelloImpl();
	}
	@Override
	public void say(String word) {
		before();
		hello.say(word);
		after();
	}
	
	private void before(){
		System.out.println("before");
	}
	
	private void after(){
		System.out.println("after");
	}
	
	public static void main(String[] args) {
		StaticHelloProxy helloProxy = new StaticHelloProxy();
		helloProxy.say("geller");
	}
}
