package com.luv2code.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    @GetMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    @GetMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    @PostMapping ("/processFormVersionTwo")
    public String letsShoutDude(@RequestParam("studentName")String name, Model theModel){
        name = name.toUpperCase();
        String result = "Yo! " + name;
        theModel.addAttribute("message",result);
        return "helloworld";
    }
}
