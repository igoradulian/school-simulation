package com.demo.firstspringapp.service;

import com.demo.firstspringapp.exception.UserExistException;
import com.demo.firstspringapp.model.ProfessorDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ProfessorService {

    public void saveProfessor(ProfessorDTO professorDTO) throws UserExistException;

    public ProfessorDTO findProfessorByEmail(String email);

    public String getFullName(ProfessorDTO professorDTO);
}
