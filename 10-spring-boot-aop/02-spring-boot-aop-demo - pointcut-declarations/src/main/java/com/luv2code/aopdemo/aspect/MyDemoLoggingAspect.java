package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        //print our method which we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);
        //get begin timestamp
        long begin = System.currentTimeMillis();

        //now, let execution the method
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        }catch (Exception exc){
            //log the exception
            System.out.println(exc.getMessage());
            //rethrow exception
            throw exc;
        }

        //get end timestamp
        long end = System.currentTimeMillis();

        //compute duration and display it
        long duration = end-begin;
        System.out.println("=====>>>Duration: " +duration/1000.0 + " seconds");
        return result;
    }

    @After("execution(* com.luv2code.aopdemo.dao.AccountDao.findAccounts(..))")
    public void afterFinallyfindAccountsAdvice(JoinPoint joinPoint){
        //print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) on method: " + method);

    }

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDao.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsAdvice(
            JoinPoint theJoinPoint, Throwable theExc){
        //print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

        //log the exception
        System.out.println("\n=====>>> The exception is: " + theExc);
    }

    //add a new advice for @AfterReturning on the findAccounts method
    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDao.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result){
        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        // print out the result of the method call
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + result);

        //let's post-process the data - let's modify it :)

        //convert the account name to UPPERCASE
        convertAccountNameToUppercase(result);

        System.out.println("\n=====>>> Executing @AfterReturning on method: " + result);

    }

    private void convertAccountNameToUppercase(List<Account> result) {
        //loop through the accounts
        for (Account account : result){
            //get uppercase version of name
            String name = account.getName().toUpperCase();
            //update the name of the account
            account.setName(name);
        }
    }


    //    this is where we add all of out related advice for logging
//
//    let's start with an @Before advice
//    @Before("execution(public void com.luv2code.aopdemo.dao.AccountDao.addAccount())")
//    @Before("execution(public void add*())")
    @Before("com.luv2code.aopdemo.aspect.LuvAopExpression.forDaoPackageNoSetterGetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint){
        System.out.println("\n=====>>> Executing @Before Advice on addAccount()");

        //display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        //display the method argument

        //get args
        Object[] args = joinPoint.getArgs();

        //loop thru args
        for(Object termArg : args){
            System.out.println(termArg);

            if(termArg instanceof Account){
                //downcast and print Account specific stuff
                Account account = (Account) termArg;
                System.out.println("Account name: " + account.getName());
                System.out.println("Account level: " + account.getLevel());
            }
        }
    }
}
