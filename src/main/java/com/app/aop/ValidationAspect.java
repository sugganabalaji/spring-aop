package com.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.app.service.JobJpaService.getJob(..)) && args(postId)")
    public Object validateAndUpdate(ProceedingJoinPoint proceedingJoinPoint, int postId) {
        if (postId < 0) {
            LOGGER.error("Input postId: {}", postId);
            postId = Math.abs(postId);
            LOGGER.info("Updated postId: {}", postId);
        }
        Object object = null;
        try {
            object = proceedingJoinPoint.proceed(new Object[]{postId});
        } catch (Throwable e) {
            LOGGER.error("Error executing method: {}", proceedingJoinPoint.getSignature(), e);
        }
        return object;
    }

}