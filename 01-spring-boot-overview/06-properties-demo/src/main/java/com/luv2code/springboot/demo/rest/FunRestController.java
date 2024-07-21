package com.luv2code.springboot.demo.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    @Value("${coach.name}")
    private String coachname;

    @Value("${team.name}")
    private String teamname;

    @GetMapping("/coachinfo")
    public String coachinfo(){
        return "Coach: " + coachname + " Team: " + teamname;
    }

    @GetMapping("/")
    public String hello(){
        return "hello world";
    }

    //expose a new endpoin for  "workout"
     @GetMapping("/workout")
    public String getDailyWorkout(){
        return "Run a hard 5k! hahahihi";
    }

    //expose a new endpoin for  "fortune"
    @GetMapping("/fortune")
    public String getDaily(){
        return "Have a good day!";
    }
}
