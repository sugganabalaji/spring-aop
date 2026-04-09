package com.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    // pointcut expression
    // return-type class-name.method-name(args)
    // execution(* *.*(..))

    @Before("execution(* com.app.service.JobJpaService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature() + " - started");
    }

    /*@Before( "execution(* com.app.service.JobJpaService.getAllJobs(..)) ||" +
            "execution(* com.app.service.JobJpaService.getJob(..))")
    public void logBefor(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName() + " - started");
    }*/

    @After("execution(* com.app.service.JobJpaService.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature() + " - ended");
    }

    @AfterReturning(pointcut = "execution(* com.app.service.JobJpaService.*(..))", returning = "returnValue")
    public void logAfterReturning(JoinPoint joinPoint, Object returnValue) {
        LOGGER.info(joinPoint.getSignature() + ": " + returnValue);
    }

    @AfterThrowing(pointcut = "execution(* com.app.service.JobJpaService.*(..))", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Exception e) {
        LOGGER.info(joinPoint.getSignature() + ": " + e.getMessage());
    }

}