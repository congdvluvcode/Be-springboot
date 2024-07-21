package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    //setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    //set up pointcut declaration
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage(){};

    //do the same for service and dao
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage(){};

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage(){};

    @Pointcut("forDaoPackage() || forControllerPackage() || forServicePackage()")
    private void forAppFlow(){};

    //add before advice
    @Before("forAppFlow()")
    private void before(JoinPoint joinPoint){
        //display method we are calling
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("=====>>> In @Before: calling method: " + method);

        //display the argument to the method
        //get the argument
        Object[] args = joinPoint.getArgs();

        //loop thru and display args
        for(Object arg : args){
            myLogger.info("=====>>> argument: " + arg);
        }
    }

    //add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult")
    private void afterReturning(JoinPoint joinPoint,Object theResult){
        //display method we are returning from
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("=====>>> In @AfterReturning: calling method: " + method);

        //display data result
        myLogger.info("=====>>> Result: " + theResult);
    }
}
