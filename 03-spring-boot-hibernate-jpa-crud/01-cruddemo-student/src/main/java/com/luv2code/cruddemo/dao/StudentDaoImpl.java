package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDaoImpl implements  StudentDao{

    //define field for entity manage
    private EntityManager entityManager;

    //inject entity manage using constructor injection
    @Autowired
    public StudentDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    //save method save
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> getAll() {
        //create query
        TypedQuery<Student> query = entityManager.createQuery("FROM Student order by lastName",Student.class);
        //return resultlist
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        //create query
        TypedQuery<Student> query = entityManager.createQuery("FROM Student where lastName = :theParamater",Student.class);
        //set paramater query
        query.setParameter("theParamater",lastName);
        //return resultlist
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student student = entityManager.find(Student.class,id);
        entityManager.remove(student);
    }
}
