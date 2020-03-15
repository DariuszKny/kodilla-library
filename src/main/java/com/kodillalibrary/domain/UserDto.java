package com.kodillalibrary.domain;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate created;
    private List<Rent> rents;
}
