package org.smart4j.framework.invocation;

public class HelloImpl implements Hello{

	@Override
	public void say(String word) {
		System.out.println("say:"+word);	
	}

}
