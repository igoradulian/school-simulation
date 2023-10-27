package com.demo.firstspringapp.config;

import com.demo.firstspringapp.model.AddressDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class StudentConfig {
    @Bean(name = "addressOne")
    @Scope("prototype")
    public AddressDTO getAddress()
    {
        return new AddressDTO("123 Main st");
    }

    @Bean(name = "addressTwo")
    @Scope("prototype")
    public AddressDTO getAddressTwo()
    {
        return new AddressDTO("345 Palmer St");
    }
}
