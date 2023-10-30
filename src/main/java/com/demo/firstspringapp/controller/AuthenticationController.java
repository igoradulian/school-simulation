package com.demo.firstspringapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {


    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }


    @RequestMapping("/home")
    public String homePage() {

        return "redirect:/list-of-students";
    }

}
