package com.ict01.jpaproject03.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentDTO {
    private int id;
    private String name;
    private String email;
    private String address;
}
