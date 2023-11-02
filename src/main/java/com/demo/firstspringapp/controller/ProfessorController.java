package com.demo.firstspringapp.controller;


import com.demo.firstspringapp.exception.UserExistException;
import com.demo.firstspringapp.model.AddressDTO;
import com.demo.firstspringapp.model.ProfessorDTO;
import com.demo.firstspringapp.model.StudentDTO;
import com.demo.firstspringapp.model.UserDTO;
import com.demo.firstspringapp.service.ProfessorService;
import com.demo.firstspringapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class ProfessorController {

    private ProfessorService professorService;
    private UserService userService;

    @Autowired
    public ProfessorController(@Qualifier("professorFullTimeServiceImpl") ProfessorService professorService,
                               UserService userService) {
        this.professorService = professorService;
        this.userService= userService;
    }

    @GetMapping("/prof/form")
    public String professorForm(Model model)
    {
        model.addAttribute("professor", new ProfessorDTO());
        model.addAttribute("user", new UserDTO());

        return "professor-sign-up";
    }


    @PostMapping("/process-professor")
    public String processingProfessor(@Valid @ModelAttribute("professor") ProfessorDTO professorDTO,
                                      BindingResult professorBindingResult,
                                      @Valid @ModelAttribute ("user") UserDTO userDTO, BindingResult
                                                  userBindingResult, Model model)
    {

        if(professorBindingResult.hasErrors())
        {

            return "professor-sign-up";
        }

        if(userBindingResult.hasErrors())
        {
            return "professor-sign-up";
        }

        try {

            professorService.saveProfessor(professorDTO);
            userDTO.setEmail(professorDTO.getEmail());
            userDTO.setRole("ROLE_PROFESSOR");
            userService.saveUser(userDTO);


        }catch (RuntimeException e)
        {
            model.addAttribute("message", "User already exist");
            return "professor-sign-up";
        } catch (UserExistException e) {
            throw new RuntimeException(e);
        }

        return "confirmation";

    }


   /* @GetMapping("/")
    public String showProfessor()
    {
        populateProfessor();
        return "professor-account";
    }

   @ModelAttribute("professor")
    public ProfessorDTO populateProfessor()
    {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setFirstName("Jack");
        professorDTO.setLastName("Smith");

        return professorDTO;
    }

    @GetMapping("/get-session")
    public String getProfessorFromSession(@ModelAttribute("professor") ProfessorDTO professorDTO)
    {

        return "profeessor-session";
    }*/

    @GetMapping("/professor-fullname")
    public String getProfessorFromSession(Model model)
    {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setFirstName("Jack");
        professorDTO.setLastName("Smith");

        model.addAttribute("name",professorService.getFullName(professorDTO));

        return "profeessor-fullname";
    }

}
