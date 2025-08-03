package com.englishacademy.aspect;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
public class LogExcutimeAspect {
    @Around("execution(* com.englishacademy.service..*(..)) || @annotation(com.englishacademy.annotation.LogExcutime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            Long endTime = System.currentTimeMillis();
            Long executionTime = endTime - startTime;
            log.info("[AOP] - {} executed in {} ms  ", joinPoint.getSignature(), executionTime);
        }

    }
}
