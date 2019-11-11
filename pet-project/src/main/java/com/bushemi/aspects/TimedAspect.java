package com.bushemi.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimedAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimedAspect.class);

    @Pointcut("@annotation(com.bushemi.annotations.Timed)")
    public void getTimedPointcut() {
    }


    @Around("getTimedPointcut() ")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        final String methodName = pjp.getSignature().getName();
        long start = System.nanoTime();
        final Object result = pjp.proceed();
        long finish = System.nanoTime();
        LOGGER.info("method name = {}, time of completing = {} nanoseconds", methodName, (finish - start));
        return result;
    }
}
