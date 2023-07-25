package com.joaogabee.springwebflux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class employeeDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
