package com.kodillalibrary.domain;

import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleDto {
    private Long id;
    private String titleName;
    private String titleAuthor;
    private int yearPublished;
    private List<BookCopy> bookCopies;
}
