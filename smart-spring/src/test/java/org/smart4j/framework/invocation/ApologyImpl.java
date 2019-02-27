package org.smart4j.framework.invocation;

import org.springframework.stereotype.Component;

@Component
public class ApologyImpl implements Apology{


	@Override
	public void saySorry(String word) {
		System.out.println("sorry!"+word);
	}
}
