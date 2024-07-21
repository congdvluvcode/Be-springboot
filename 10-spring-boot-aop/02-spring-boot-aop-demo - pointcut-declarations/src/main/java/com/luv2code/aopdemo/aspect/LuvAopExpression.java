package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
public class LuvAopExpression {
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {};
    //create pointcut for getter/setter method
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
    public void getter(){};

    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
    public void setter(){};

    @Pointcut("forDaoPackage()&&!(setter()||getter())")
    public void forDaoPackageNoSetterGetter(){};
}
