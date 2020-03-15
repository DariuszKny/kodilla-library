package com.kodillalibrary.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCopyDto {
    private Long id;
    private TitleDto title;
    private BookCopyStatus status;
}
