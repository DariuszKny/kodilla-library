package com.kodillalibrary.domain;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentDto {
    private Long id;
    private User user;
    private BookCopy bookCopy;
    private LocalDate rentedDate;
    private LocalDate returnDate;
}
