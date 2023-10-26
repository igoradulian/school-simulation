package com.demo.firstspringapp.config;

import com.demo.firstspringapp.model.AddressDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    public AddressDTO getAddress()
    {
        return new AddressDTO("123 Main");
    }
}
