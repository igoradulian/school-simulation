package com.demo.firstspringapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private String email;
    private String login;
    private String password;
    private String matchPassword;
}
