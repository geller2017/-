package org.smart4j.framework.test;



//import java.util.Iterator;
//import java.util.Set;

import org.smart4j.framework.invocation.Apology;
import org.smart4j.framework.invocation.Hello;
import org.smart4j.framework.invocation.HelloImpl;
import org.smart4j.framework.util.HelperLoader;
//import org.smart4j.framework.util.ClassUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		/*String packageName = "org.smart4j.framework";*/
		/*Set<Class<?>> classes = ClassHelper.getClassSet();
		Iterator<Class<?>> classIter = classes.iterator();
		while(classIter.hasNext()){
			System.out.println(classIter.next().getName());
		}*/
		/*@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop.xml");
		Hello hello = (Hello) context.getBean(HelloImpl.class);
		hello.say("hehe");*/
		
		/*ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop.xml");
		ApologyImpl apologyImpl = (ApologyImpl) context.getBean(ApologyImpl.class);
		apologyImpl.say("hehe");*/
		
		/*ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop.xml");
		Hello hello = (HelloImpl) context.getBean(HelloImpl.class);
		hello.say("geller");
		System.out.println("==================================");
		Apology apologyImpl = (Apology)hello;
		apologyImpl.saySorry("geller");*/
		
		HelperLoader.init();
		
	}
}
