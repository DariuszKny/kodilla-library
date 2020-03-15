package com.kodillalibrary.repository;

import com.kodillalibrary.domain.BookCopy;
import com.kodillalibrary.domain.BookCopyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    @Override
    List<BookCopy> findAll();

    @Override
    Optional<BookCopy> findById(Long aLong);

    Long countBookCopyByTitle_IdAndStatus(Long titleId, BookCopyStatus status);

    @Override
    BookCopy save(BookCopy bookCopy);

    @Override
    void deleteById(Long aLong);


}
