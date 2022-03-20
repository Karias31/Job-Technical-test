package com.barousse.project.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	
	@Before("execution(public * com.barousse.project.service.*Service.*(..))")
	public void log(JoinPoint joinPoint) {
		
		
		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		long duree = end - start;
		
	    System.out.printf("\n"+"Call the method  %s with %d parameters%n",
	                      joinPoint.toShortString(),
	                      joinPoint.getArgs().length);
	  
  }
	

}
