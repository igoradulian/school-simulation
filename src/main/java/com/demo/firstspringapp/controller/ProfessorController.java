package com.demo.firstspringapp.controller;


import com.demo.firstspringapp.model.ProfessorDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/prof")
@SessionAttributes("professor")
public class ProfessorController {


    @GetMapping("/")
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
    }

}
