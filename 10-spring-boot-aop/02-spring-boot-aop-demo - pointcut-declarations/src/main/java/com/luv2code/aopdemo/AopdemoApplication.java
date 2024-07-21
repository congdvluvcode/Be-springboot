package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDao;
import com.luv2code.aopdemo.dao.MembershipDao;
import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDao accountDao, MembershipDao membershipDao, TrafficFortuneService trafficFortuneService){
		return runner->{
//			demoTheBeforeAdvice(accountDao,membershipDao);
//			demoTheAfterReturningAdvice(accountDao);
//			demoTheAfterThrowingAdvice(accountDao);
//			demoTheAfterAdvice(accountDao);
//			demoTheAroundAdvice(trafficFortuneService);
//			demoTheAroundAdviceHandleException(trafficFortuneService);
    		demoTheAroundAdviceThrowException(trafficFortuneService);
		};

	}

	private void demoTheAroundAdviceThrowException(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain Program: demoTheAroundAdviceThrowException");

		System.out.println("Calling getFortune()");
		boolean tripWire=true;
		String data = trafficFortuneService.getFortune(tripWire);
		System.out.println("My fortune is " + data);
		System.out.println("Finished");
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain Program: demoTheAroundAdviceHandleException");

		System.out.println("Calling getFortune()");
		boolean tripWire=true;
		String data = trafficFortuneService.getFortune(tripWire);
		System.out.println("My fortune is " + data);
		System.out.println("Finished");
	}

	private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain Program: demoTheAroundAdvice");

		System.out.println("Calling getFortune()");
		String data = trafficFortuneService.getFortune();
		System.out.println("My fortune is " + data);
		System.out.println("Finished");
	}

	private void demoTheAfterAdvice(AccountDao accountDao) {
		//call method findAccounts
		List<Account> accounts= null;
		try {
			//add a boolean flag to simulate exception
			boolean tripWire =false;
			accounts = accountDao.findAccounts(tripWire);
		}catch (Exception exe){
			System.out.println("\n\nMain Program....catch exception: " + exe);
		}
		//display the accounts
		System.out.println("\n\nMain Program: demoTheAfterAdvice");
		System.out.println("----");

		System.out.println(accounts);

		System.out.println("\n");
	}

	private void demoTheAfterThrowingAdvice(AccountDao accountDao) {
		//call method findAccounts
		List<Account> accounts= null;
		try {
			//add a boolean flag to simulate exception
			boolean tripWire =true;
			accounts = accountDao.findAccounts(tripWire);
		}catch (Exception exe){
			System.out.println("\n\nMain Program....catch exception: " + exe);
		}
		//display the accounts
		System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
		System.out.println("----");

		System.out.println(accounts);

		System.out.println("\n");
	}

	private void demoTheAfterReturningAdvice(AccountDao accountDao) {
		//call method findAccounts
		List<Account> accounts = accountDao.findAccounts();

		//display the accounts
		System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
		System.out.println("----");

		System.out.println(accounts);

		System.out.println("\n");
	}

	private void demoTheBeforeAdvice(AccountDao accountDao,MembershipDao membershipDao) {
		Account account = new Account();
		accountDao.addAccount(account,true);
		accountDao.test1();
		accountDao.setName("");
		accountDao.setServiceCode("");
		accountDao.getName();
		accountDao.getServiceCode();
		System.out.println("let's call it again!");
		membershipDao.addSillyMember();
		membershipDao.test2();
	}
}
