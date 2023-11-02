package com.demo.firstspringapp.model;

import com.demo.firstspringapp.validation.FieldMatch;
import com.demo.firstspringapp.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@FieldMatch.List({@FieldMatch(first = "password", second = "matchPassword", message = "Password fields don't match")})
public class UserDTO {

    @Email(message = "Please provide email")
    private String email;

    @NotEmpty(message = "Please Provide Login")
    private String login;

    @NotEmpty(message = "Password field can't be empty")
    @ValidPassword(message = "Provide complex password")
    private String password;
    private String matchPassword;

    private String role;
}
