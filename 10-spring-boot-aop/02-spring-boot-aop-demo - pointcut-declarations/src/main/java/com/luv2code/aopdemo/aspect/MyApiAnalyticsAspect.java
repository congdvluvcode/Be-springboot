package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAspect {
    @Before("com.luv2code.aopdemo.aspect.LuvAopExpression.forDaoPackageNoSetterGetter()")
    public void performApiAnalytics(){
        System.out.println("\n=====>>> Performing Api Analytics");
    }

}
