package com.demo.firstspringapp.controller;


import com.demo.firstspringapp.model.ProfessorDTO;
import com.demo.firstspringapp.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/prof")
@SessionAttributes("professor")
public class ProfessorController {

    private ProfessorService professorService;

    @Autowired
    public ProfessorController(@Qualifier("professorFullTimeServiceImpl") ProfessorService professorService) {
        this.professorService = professorService;
    }

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
