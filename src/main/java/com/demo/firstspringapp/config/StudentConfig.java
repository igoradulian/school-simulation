package com.demo.firstspringapp.config;

import com.demo.firstspringapp.model.Address;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    public Address getAddress()
    {
        return new Address("123 Main");
    }
}
