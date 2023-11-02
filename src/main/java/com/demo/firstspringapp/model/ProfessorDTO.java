package com.demo.firstspringapp.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfessorDTO {

    @NotEmpty(message = "First Name must be provided")
    private String firstName;

    @NotEmpty(message = "Last Name must be provided")
    private String lastName;

    @Email(message = "Provide correct email")
    private String email;
}
