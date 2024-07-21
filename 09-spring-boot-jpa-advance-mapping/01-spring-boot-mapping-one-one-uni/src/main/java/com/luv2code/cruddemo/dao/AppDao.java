package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

public interface AppDao {
    void save(Instructor theInstructor);
    Instructor findById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorById(int id);
    void deleteInstructorDetailById(int id);
}
