package com.demo.firstspringapp.service;

import com.demo.firstspringapp.entity.Professor;
import com.demo.firstspringapp.model.ProfessorDTO;
import com.demo.firstspringapp.model.StudentDTO;
import com.demo.firstspringapp.repository.ProfessorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorPartTimeServiceImpl implements ProfessorService {

    private ProfessorRepository professorRepository;

    @Autowired
    public ProfessorPartTimeServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public void saveProfessor(ProfessorDTO professorDTO) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Professor professor = modelMapper.map(professorDTO, Professor.class);

        professorRepository.save(professor);
    }

    @Override
    public ProfessorDTO findProfessorByEmail(String email) {
        Optional<Professor> professorOptional =  professorRepository.findProfessorByEmail(email);

        if(professorOptional.isPresent()){

            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            ProfessorDTO professorDTO = modelMapper.map(professorOptional.get(), ProfessorDTO.class);
            return professorDTO;
        }
        else
            throw new UsernameNotFoundException("User Not Found");
    }

    @Override
    public String getFullName(ProfessorDTO professorDTO) {
        return professorDTO.getFirstName() + " " + professorDTO.getLastName();
    }
}
