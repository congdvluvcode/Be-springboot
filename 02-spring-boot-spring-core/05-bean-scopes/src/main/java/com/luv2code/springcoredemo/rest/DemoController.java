package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;

@RestController
public class DemoController {
    private Coach myCoach;
    private Coach anoCoach;

    @Autowired
    public DemoController(
            @Qualifier("baseballCoach")Coach theCoach,
            @Qualifier("baseballCoach")Coach anoTheCoach
            ){
        System.out.println("In constructor: " + getClass().getSimpleName());
        myCoach = theCoach;
        anoCoach=anoTheCoach;
    }

    @GetMapping("/DailyWorkout")
    public String getDaiLyWorkout(){
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check(){
        return "Compare myCoach=anoCoach," + (anoCoach==myCoach);
    }
}
