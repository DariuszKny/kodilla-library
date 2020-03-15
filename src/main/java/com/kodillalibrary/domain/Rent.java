package com.kodillalibrary.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "rent")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @NonNull
    private User user;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "bookCopy_ID")
    @NonNull
    private BookCopy bookCopy;

    @Column(name = "rented_date")
    private LocalDate rentedDate;
    @Column(name = "return_date")
    private LocalDate returnDate;
}
