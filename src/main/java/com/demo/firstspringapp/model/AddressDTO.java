package com.demo.firstspringapp.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class AddressDTO {


    private String streetName;

}
