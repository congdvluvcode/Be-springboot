package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoJpaImpl implements EmployeeDao{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDaoJpaImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> getAll() {
        //create query
        TypedQuery<Employee> query = entityManager.createQuery("From Employee ",Employee.class);
        //return list
        return query.getResultList();
    }

    @Override
    public Employee getById(Integer id) {
        return entityManager.find(Employee.class,id);
    }

    @Override
    public Employee save(Employee employee) {
        Employee db =  entityManager.merge(employee);
        return db;
    }

    @Override
    public void delete(Integer id) {
        Employee employee = entityManager.find(Employee.class,id);
        entityManager.remove(employee);
    }

}
