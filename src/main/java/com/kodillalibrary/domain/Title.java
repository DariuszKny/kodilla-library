package com.kodillalibrary.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity(name = "title")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titleName")
    @NotNull
    private String titleName;
    @Column(name = "titleAuthor")
    @NotNull
    private String titleAuthor;
    @Column(name = "yearPublished")
    @NotNull
    private int yearPublished;

    @OneToMany(
            targetEntity = BookCopy.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<BookCopy> bookCopies = new ArrayList<>();
}
