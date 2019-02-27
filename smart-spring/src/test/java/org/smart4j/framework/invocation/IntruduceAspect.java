package org.smart4j.framework.invocation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class IntruduceAspect {
	@DeclareParents(value="org.smart4j.framework.invocation.HelloImpl",defaultImpl=ApologyImpl.class)
	private Apology apology;
}
