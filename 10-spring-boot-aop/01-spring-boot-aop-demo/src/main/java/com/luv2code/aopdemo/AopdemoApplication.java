package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDao;
import com.luv2code.aopdemo.dao.MembershipDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDao accountDao, MembershipDao membershipDao){
		return runner->{
			demoTheBeforeAdvice(accountDao,membershipDao);
		};

	}

	private void demoTheBeforeAdvice(AccountDao accountDao,MembershipDao membershipDao) {
		Account account = new Account();
		accountDao.addAccount(account,true);
		accountDao.test1();
		System.out.println("let's call it again!");
		membershipDao.addSillyMember();
		membershipDao.test2();
	}

}
