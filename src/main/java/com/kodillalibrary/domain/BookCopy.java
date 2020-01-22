package com.kodillalibrary.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bookCopy")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "title_id", referencedColumnName = "id")
    @NonNull
    private Title title;

    @Column(name = "status")
    @NonNull
    private BookCopyStatus status;
}
