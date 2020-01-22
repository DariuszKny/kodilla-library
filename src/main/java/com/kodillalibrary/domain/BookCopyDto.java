package com.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCopyDto {
    private Long id;
    private Title title;
    private BookCopyStatus status;

}
