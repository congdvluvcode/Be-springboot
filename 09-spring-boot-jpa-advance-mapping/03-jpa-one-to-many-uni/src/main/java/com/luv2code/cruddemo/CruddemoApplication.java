package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDao;
import com.luv2code.cruddemo.entity.*;
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
	public CommandLineRunner commandLineRunner(AppDao appDao){
		return runner->{
//			createInstructor(appDao);
//			findInstructor(appDao);
//			deleteInstructor(appDao);
//			findInstructorDetail(appDao);
//			deleteInstructorDetail(appDao);
//			createInstructorWithCourses(appDao);
//			findInstructorWithCourses(appDao);
//			findCoursesForInstructor(appDao);
//			findInstructorWithCoursesJoinFetch(appDao);
//			updateInstructor(appDao);
//			updateCourse(appDao);
//			deleteCourse(appDao);
//			createCourseAndReview(appDao);
//			retrieveCourseAndReviews(appDao);
//			createCourseAndStudent(appDao);
//			retrieveCourseAndStudents(appDao);
//			retrieveStudentAndCourses(appDao);
			addMoreCoursesForStudent(appDao);
		};
	}

	private void addMoreCoursesForStudent(AppDao appDao) {
		//find student
		int id=2;
		Student student = appDao.findStudentAndCourseByStudentId(id);

		//create some courses
		Course tempCourse1 = new Course("Rubik's cube - How to speed Cube");
		Course tempCourse2 = new Course("Atari 2600 - Game Development");

		//add courses for student
		System.out.println("saving student: " + student);
		System.out.println("associate course: " + student.getCourses());
		student.addCourse(tempCourse1);
		student.addCourse(tempCourse2);
		appDao.update(student);

		System.out.println("Done!");
	}

	private void retrieveCourseAndStudents(AppDao appDao) {
		//get course and student
		int id=10;
		System.out.println("finding course id: " + id);
		Course course = appDao.findCourseAndStudentByCourseId(id);
		//print course and student
		System.out.println("course: " + course);
		System.out.println("the associate student: " + course.getStudents());

		System.out.println("Done!");
	}

	private void retrieveStudentAndCourses(AppDao appDao) {
		//get course and student
		int id=2;
		System.out.println("finding student id: " + id);
		Student student = appDao.findStudentAndCourseByStudentId(id);
		//print course and student
		System.out.println("student: " + student);
		System.out.println("the associate course: " + student.getCourses());

		System.out.println("Done!");
	}

	private void createCourseAndStudent(AppDao appDao) {
		//create course
		Course course = new Course("Pacman - How to score One Million Point");

		//create student
		Student student1 = new Student("John","Doe","john@luv2code.com");
		Student student2 = new Student("Mary","Public","mary@luv2code.com");

		//add student to course
		course.addStudent(student1);
		course.addStudent(student2);

		//save course and associate student
		System.out.println("Saving the course: " + course);
		System.out.println("Associate student: " + course.getStudents());
		appDao.save(course);
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDao appDao) {
		//get course and review
		int id=10;
		Course course = appDao.findCourseAndReviewByCourseId(id);
		//print course and reviews
		System.out.println("Course: " + course);
		System.out.println("Reviews: " + course.getReviews());

		System.out.println("Done!");
	}

	private void createCourseAndReview(AppDao appDao) {
		//create course
		Course course = new Course("Pacman - How to score One Million Point");
		//add some review to course
		course.addReview(new Review("Great course ... loved it!"));
		course.addReview(new Review("Cool course, job well done!"));
		course.addReview(new Review("What a dumb course, you are an idiot!"));
		//save course
		System.out.println("Saving the course");
		System.out.println(course);
		System.out.println(course.getReviews());
		appDao.save(course);
		System.out.println("Done!");
	}

	private void deleteCourse(AppDao appDao) {
		int id =10;
		System.out.println("deleting course id: " +id);
		appDao.deleteCourseById(id);

		System.out.println("Done!");
	}

	private void updateCourse(AppDao appDao) {
		int id=10;
		//find course
		System.out.println("finding course id: " + id);
		Course course  = appDao.findCourse(id);
		//update course
		System.out.println("updating course id: " + id);
		course.setTitle("like simple thing");
		appDao.update(course);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDao appDao) {
		int theId=1;
		//find ínstructor
		System.out.println("finding instructor id: " + theId);
		Instructor tempInstructor = appDao.findById(theId);

		//update instructor
		System.out.println("updating instructor id: " + theId);
		tempInstructor.setLastName("TESTER");

		appDao.update(tempInstructor);

		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDao appDao) {
		int theId=1;
		System.out.println("find id instructor: " + theId);
		Instructor tempInstructor = appDao.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associate courses : " + tempInstructor.getCourses());
		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDao appDao) {
		int theId=1;
		System.out.println("find id instructor: " + theId);
		Instructor tempInstructor = appDao.findById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		//find courses for instructor
		System.out.println("Finding courses for instructor id " + theId);
		List<Course> courses = appDao.findCoursesByInstructorId(theId);
		//associate the object
		tempInstructor.setCourses(courses);
		System.out.println("the associate courses : " + tempInstructor.getCourses());
		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDao appDao) {
		int theId=1;
		System.out.println("find id instructor: " + theId);
		Instructor tempInstructor = appDao.findById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associate courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDao appDao) {
		Instructor termInstructor = new Instructor("Chad","Darby","darby@luv2code.com");

		// create instructor detail
		InstructorDetail termInstructorDetail =
				new InstructorDetail("https://mail.google.com/mail/u/0/?tab=rm&ogbl#inbox","Luv 2 code!!");

		// associate the object
		termInstructor.setInstructorDetail(termInstructorDetail);

		// create some courses
		Course tempCourse1= new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2= new Course("The Pinball Masterclass");

		// add courses to instructor
		termInstructor.addCourse(tempCourse1);
		termInstructor.addCourse(tempCourse2);

		// save the instructor
		// this will also save the courses
		// because of cascade.persis
		System.out.println("Saving instructor: " + termInstructor);
		System.out.println("The Courses: " + termInstructor.getCourses());

		appDao.save(termInstructor);
		System.out.println("Done!");
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
