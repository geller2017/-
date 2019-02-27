package org.smart4j.framework.invocation;

import org.springframework.stereotype.Component;

@Component
public class HelloImpl implements Hello{

	@Override
	public void say(String word) {
		System.out.println("say:"+word);
	}
	
	public void goodMorning(){
		System.out.println("good morning!");
	}
	
	public void goodNight(){
		System.out.println("good night!");
	}
	
	public void greeting(){
		System.out.println("how are you!");
	}

}
