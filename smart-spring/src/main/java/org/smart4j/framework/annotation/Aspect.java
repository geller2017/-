package org.smart4j.framework.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/***
 * @author Administrator
 *	一个基于切面注解的aop框架
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
	/***
	 * 该注解中包含一个value的属性，它是一个注解类，用来定义Controller这类注解
	 * @return
	 */
	Class<? extends Annotation> value();
}
