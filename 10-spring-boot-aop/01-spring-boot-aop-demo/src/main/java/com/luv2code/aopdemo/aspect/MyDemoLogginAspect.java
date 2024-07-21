package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLogginAspect {
    //this is where we add all of out related advice for logging

    //let's start with an @Before advice
//    @Before("execution(public void com.luv2code.aopdemo.dao.AccountDao.addAccount())")
//    @Before("execution(public void add*())")
    @Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice(){
        System.out.println("\n=====>>> Executing @Before Advice on addAccount()");
    }
}
