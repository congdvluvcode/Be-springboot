package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDao;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao){
		return runner ->{
			// run when spring bean loadded
			// getAllStudent(studentDao);
//			updateStudent(studentDao);
			createStudent(studentDao);
		};
	}

	private void updateStudent(StudentDao studentDao) {
		Integer id = 1;
		//retrieving student base on id
		System.out.println("Getting student with id: " + id);
		Student student = studentDao.findById(id);

		//chang first name
		System.out.println("Updating student ...");
		student.setFirstName("Scoppy");
		//update student
		studentDao.update(student);
		//display
		System.out.println("Update student : " + student);
	}

	private void getAllStudent(StudentDao studentDao) {
		//get list student
		List<Student> list = studentDao.findByLastName("do");
		//display list student
		for (Student student : list){
			System.out.println(student);
		}

	}

	private void readStudent(StudentDao studentDao) {
		//create new student object
		System.out.println("Creating new student object...");
		Student termStudent = new Student("nhan","do","nhanph31357");

		//save the student object
		System.out.println("Saving the student...");
		studentDao.save(termStudent);

		//display id of the saved student
		int id = termStudent.getId();
		System.out.println("Saved student. Generated id: " + id);

		//retrieving the student base on id
		System.out.println("Retrieving student with id : " + id);
		Student student = studentDao.findById(id);

		//display student
		System.out.println("Found the student : " + student);
	}

	private void createStudent(StudentDao studentDao) {
		//create new student object
		System.out.println("Creating new student object...");
		Student termStudent = new Student("cong","do","congph31357");

		//save the student object
		System.out.println("Saving the student...");
		studentDao.save(termStudent);

		//display id of the saved student
		System.out.println("Saved student. Generated id: " + termStudent.getId());
	}
}
