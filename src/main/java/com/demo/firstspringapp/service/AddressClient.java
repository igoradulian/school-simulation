package com.demo.firstspringapp.service;

import com.demo.firstspringapp.model.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AddressClient {

    @Autowired
    @Qualifier("addressTwo")
    AddressDTO addressDTO;

    public String printAddressOfBean()
    {

        System.out.println(addressDTO.getStreetName());
        return addressDTO.getStreetName();
    }
}
