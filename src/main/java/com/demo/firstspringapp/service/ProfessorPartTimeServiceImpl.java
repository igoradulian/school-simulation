package com.demo.firstspringapp.service;

import com.demo.firstspringapp.model.ProfessorDTO;
import org.springframework.stereotype.Service;

@Service
public class ProfessorPartTimeServiceImpl implements ProfessorService {

    @Override
    public String getFullName(ProfessorDTO professorDTO) {
        return professorDTO.getFirstName() + " " + professorDTO.getLastName();
    }
}
