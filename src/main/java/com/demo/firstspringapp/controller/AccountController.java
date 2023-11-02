package com.demo.firstspringapp.controller;


import com.demo.firstspringapp.entity.Role;
import com.demo.firstspringapp.entity.User;
import com.demo.firstspringapp.model.ProfessorDTO;
import com.demo.firstspringapp.model.StudentDTO;
import com.demo.firstspringapp.service.ProfessorService;
import com.demo.firstspringapp.service.StudentService;
import com.demo.firstspringapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Controller
public class AccountController {

    private StudentService studentService;

    private ProfessorService professorService;

    private UserService userService;

    @Autowired
    public AccountController(StudentService studentService,@Qualifier("professorFullTimeServiceImpl") ProfessorService professorService, UserService userService) {
        this.studentService = studentService;
        this.professorService = professorService;
        this.userService = userService;
    }

    @GetMapping("/account")
    public String userAccount(@AuthenticationPrincipal UserDetails userDetails, Model model)
    {

        User user = userService.findUserByEmail(userDetails.getUsername());

        List<Role> roleList = user.getRoles().stream().collect(Collectors.toList());


        if(roleList.get(0).getName().equals("ROLE_STUDENT")) {
            StudentDTO studentDTO = studentService.findStudentByEmail(userDetails.getUsername());
            model.addAttribute("student", studentDTO);
        }
        else
        {
            ProfessorDTO professorDTO = professorService.findProfessorByEmail(userDetails.getUsername());
            model.addAttribute("professor", professorDTO);
        }

        return "account";
    }

}
