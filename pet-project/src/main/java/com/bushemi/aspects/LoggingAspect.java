package com.bushemi.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(public * com.bushemi.service..*(..))")
    public void getLoggingPointcut() {
    }

    @Before("getLoggingPointcut()")
    public void beforeMethod(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String name = jp.getSignature().getName();
        LOGGER.info("name = {}, arguments = {}", name, Arrays.toString(args));
    }

    @AfterReturning(value = "getLoggingPointcut()", returning = "result")
    public void afterMethod(Object result) {
        LOGGER.info("the result = {}", result);
    }
}
