package com.kodillalibrary.domain;

import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TitleDto {
    @NotNull
    private Long id;
    @NotNull
    private String titleName;
    @NotNull
    private String titleAuthor;
    @NotNull
    private int yearPublished;
    @NonNull
    private List<BookCopy> bookCopies;

}
