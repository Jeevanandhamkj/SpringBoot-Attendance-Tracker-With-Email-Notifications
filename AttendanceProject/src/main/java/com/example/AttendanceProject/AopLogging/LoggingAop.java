package com.example.AttendanceProject.AopLogging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAop {

    Logger logger= LoggerFactory.getLogger(LoggingAop.class);
   // @Pointcut("execution(* com.example.AttendanceProject..*(..))|| execution(*com.example.AttendanceProject..*(..))|| execution(*com.example.AttendanceProject.
   @Pointcut("execution(* com.example.AttendanceProject.controller..*(..)) || execution(* com.example.AttendanceProject.service..*(..)) || execution(* com.example.AttendanceProject.repository..*(..))")
    public void pointcut(){

    }

@Around("pointcut()")
    public Object loggers(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper mapper=new ObjectMapper();
        String meth=pjp.getSignature().getName();
        String classname=pjp.getTarget().getClass().getSimpleName();
        Object[]arg= pjp.getArgs();
        logger.info(meth+classname+mapper.writeValueAsString(arg));

        Object Res=pjp.proceed();
        logger.info(meth+classname+mapper.writeValueAsString(Res));
        return Res;
    }
}
