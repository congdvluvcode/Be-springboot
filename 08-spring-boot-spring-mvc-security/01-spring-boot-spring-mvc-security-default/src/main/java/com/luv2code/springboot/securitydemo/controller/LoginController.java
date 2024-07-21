package com.luv2code.springboot.securitydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String myLoginPage(){
        return "fancy-login";
    }

    //add request mapping for access-denied page
    @GetMapping("access-denied")
    public String showAccessDenied(){
        return "access-denied";
    }
}
