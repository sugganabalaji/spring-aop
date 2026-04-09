package com.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitorAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitorAspect.class);

    @Around("execution(* com.app.service.JobJpaService.*(..))")
    public Object calculateExecutionTime(ProceedingJoinPoint proceedingJoinPoint) {
        long startTime = System.currentTimeMillis();
        Object object = null;
        try {
            object = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            LOGGER.error("Error executing method: {}", proceedingJoinPoint.getSignature(), e);
        }
        long endTime = System.currentTimeMillis();
        LOGGER.info("{} - Execution time: {} ms", proceedingJoinPoint.getSignature(), endTime - startTime);
        return object;
    }

}