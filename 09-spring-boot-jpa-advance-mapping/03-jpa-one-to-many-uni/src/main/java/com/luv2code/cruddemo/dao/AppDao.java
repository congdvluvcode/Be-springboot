package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;

import java.util.List;

public interface AppDao {
    void save(Instructor theInstructor);
    void update(Instructor theInstructor);
    Instructor findById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorById(int id);
    void deleteInstructorDetailById(int id);
    List<Course> findCoursesByInstructorId(int id);
    Instructor findInstructorByIdJoinFetch(int id);
    Course findCourse(int id);
    void update(Course course);
    void deleteCourseById(int id);
    void save(Course course);
    Course findCourseAndReviewByCourseId(int id);
    Course findCourseAndStudentByCourseId(int id);
    Student findStudentAndCourseByStudentId(int id);
    void update(Student student);
}
