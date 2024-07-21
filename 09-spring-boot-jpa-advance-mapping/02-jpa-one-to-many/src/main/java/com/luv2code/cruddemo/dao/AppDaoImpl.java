package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao{
    //define field for entity manager
    private EntityManager entityManager;
    //inject entity manager using constructor injection
    public AppDaoImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    @Transactional
    public void update(Instructor theInstructor) {
        entityManager.merge(theInstructor);
    }

    @Override
    public Instructor findById(int id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        //find Instructor by Id;
        Instructor instructor = entityManager.find(Instructor.class,id);
        //get course
        List<Course> courses=instructor.getCourses();
        //remove instructor from all associate course
        for (Course tempCourse : courses){
            tempCourse.setInstructor(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class,id);
        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("from Course  where instructor.id = :data", Course.class);
        query.setParameter("data",id);
        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                                                "select i from Instructor i "
                                                        + "JOIN FETCH i.courses "
                                                        + "where i.id = :data", Instructor.class
        );
        query.setParameter("data",id);
        return  query.getSingleResult();
    }

    @Override
    public Course findCourse(int id) {
        return entityManager.find(Course.class,id);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        //find course
        Course course = entityManager.find(Course.class,id);
        entityManager.remove(course);


    }
}
