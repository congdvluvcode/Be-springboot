package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;

import java.util.List;

public interface StudentDao {
    void save(Student student);

    Student findById(Integer id);

    List<Student> getAll();

    List<Student> findByLastName(String lastName);

    void update(Student student);

    void delete(Integer id);

}
