package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

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
}
