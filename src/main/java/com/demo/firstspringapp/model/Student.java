package com.demo.firstspringapp.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Student {


    @NotEmpty(message = "Can't be empty")
    private String firstName;

    @NotEmpty(message = "Can't be empty")
    private String lastName;

    @Email(message = "Wrong formatting")
    @NotEmpty(message = "Can't be empty")
    private String email;

    private Address address;

    private String enabled;

}
