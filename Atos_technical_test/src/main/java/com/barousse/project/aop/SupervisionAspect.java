package com.barousse.project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SupervisionAspect {
	
	
	 @Around("@annotation(supervision)")
	  public Object superviser(ProceedingJoinPoint joinPoint, Supervision supervision)
	                      throws Throwable {
	    long start = System.currentTimeMillis();
	    try {
	      return joinPoint.proceed(joinPoint.getArgs());
	    } finally {
	      long end = System.currentTimeMillis();
	      long duree = end - start;
	        System.out.printf("  %s's runtime lasted for %dms %n",
	                          joinPoint.toShortString(), duree);
	      
	    }
	  }

}
