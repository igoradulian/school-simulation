package com.demo.firstspringapp.service;

import com.demo.firstspringapp.model.ProfessorDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ProfessorService {

    public String getFullName(ProfessorDTO professorDTO);
}
