package com.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RentDto {
    private Long id;
    private User user;
    private BookCopyDto bookCopy;
    private LocalDate rented;
    private LocalDate gotBack;
}
