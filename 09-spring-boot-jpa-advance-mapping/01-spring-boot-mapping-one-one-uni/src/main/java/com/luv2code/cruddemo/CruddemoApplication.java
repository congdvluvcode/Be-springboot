package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDao;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao){
		return runner->{
//			createInstructor(appDao);
//			findInstructor(appDao);
//			deleteInstructor(appDao);
//			findInstructorDetail(appDao);
			deleteInstructorDetail(appDao);
		};
	}

	private void deleteInstructorDetail(AppDao appDao) {
		int theId = 3;
		System.out.println("Deleting InstructorDetail id : " + theId);
		appDao.deleteInstructorDetailById(theId);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDao appDao) {
		//get the instructorDetail object
		int id=2;
		InstructorDetail instructorDetail = appDao.findInstructorDetailById(id);
		//print instructorDetail
		System.out.println("termInstructorDetail: "+instructorDetail);
		//print associate instructor
		System.out.println("termInstructor: "+instructorDetail.getInstructor());
		System.out.println("Done!");
	}

	private void deleteInstructor(AppDao appDao) {
		int theId = 1;
		System.out.println("Deleting Instructor id : " + theId);
		appDao.deleteInstructorById(theId);
		System.out.println("Done!");
	}

	private void createInstructor(AppDao appDao) {
		//create new instructor
		Instructor termInstructor = new Instructor("Chad","Darby","darby@luv2code.com");

		//create instructor detail
		InstructorDetail termInstructorDetail =
				new InstructorDetail("https://mail.google.com/mail/u/0/?tab=rm&ogbl#inbox","Luv 2 code!!");

		//associate the object
		termInstructor.setInstructorDetail(termInstructorDetail);

		//save instructor
		//note : this will also save the instructor detail
		//because of CascadeType.All
		System.out.println("Save instructor : " + termInstructor);
		appDao.save(termInstructor);

		System.out.println("Done!");
	}

	private void findInstructor(AppDao appDao) {
		//define id
		int id=1;
		System.out.println("find constructor id : " + id);
		//find instructor
		Instructor termInstructor = appDao.findById(id);
		System.out.println("termInstructor" + termInstructor);
		System.out.println("the associate instructor detail only" + termInstructor.getInstructorDetail());
	}
}
