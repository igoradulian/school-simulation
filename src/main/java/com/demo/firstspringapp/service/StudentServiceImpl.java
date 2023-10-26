package com.demo.firstspringapp.service;

import com.demo.firstspringapp.entity.Address;
import com.demo.firstspringapp.entity.Student;
import com.demo.firstspringapp.model.AddressDTO;
import com.demo.firstspringapp.model.StudentDTO;
import com.demo.firstspringapp.repository.AddressRepository;
import com.demo.firstspringapp.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService{

    /**
     * This map is used now instead real database
     * just to keep things easy
     */
    private static final Map<String, StudentDTO> studentDB = new HashMap<>();

    private StudentRepository studentRepository;

    private AddressRepository addressRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, AddressRepository addressRepository) {
        this.studentRepository = studentRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void saveStudent(StudentDTO studentDTO, AddressDTO addressDTO) {

        //studentDB.put(studentDTO.getEmail(), studentDTO);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Student student = modelMapper.map(studentDTO, Student.class);
        Address address = modelMapper.map(addressDTO, Address.class);

        addressRepository.save(address);
        student.setAddress(address);
        studentRepository.save(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentDB.values().stream().toList();
    }

    @Override
    public StudentDTO findStudentByEmail(String email) {
        return studentDB.get(email);
    }
}
