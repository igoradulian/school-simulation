package com.demo.firstspringapp;


import com.demo.firstspringapp.model.AddressDTO;
import com.demo.firstspringapp.model.StudentDTO;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ConfigTest {

    @Bean
    public StudentDTO getTestStudentDTOObject()
    {
        StudentDTO studentDTO = new StudentDTO();

        studentDTO = new StudentDTO();
        studentDTO.setEmail("test@test.com");
        studentDTO.setEnabled("Yes");
        studentDTO.setFirstName("Igor");
        studentDTO.setLastName("Adulyan");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO = new AddressDTO();
        addressDTO.setStreetName("123 Main st");

        studentDTO.setAddress(addressDTO);

        return studentDTO;
    }
}
