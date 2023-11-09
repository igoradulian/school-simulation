package com.demo.firstspringapp.entity;


import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    private Address address;

    private String enabled;

    private String login;
}
