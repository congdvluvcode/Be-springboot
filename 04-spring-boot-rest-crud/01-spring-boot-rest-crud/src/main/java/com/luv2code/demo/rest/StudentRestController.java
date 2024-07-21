package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> list = new ArrayList<>();

    @PostConstruct
    public void loadData(){
        list.add(new Student("cong1","do"));
        list.add(new Student("nhan","van"));
        list.add(new Student("trang","do"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return list;
    }

    @GetMapping ("/students/{studentId}")
    public Student getStudents(@PathVariable int studentId){
        if(studentId>list.size() || studentId<0){
            throw new StudentNotFoundException("Student is not found - " + studentId);
        }
        return list.get(studentId);
    }


}
