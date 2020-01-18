package com.kodillalibrary.repository;

import com.kodillalibrary.domain.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    @Override
    List<BookCopy> findAll();

    @Override
    Optional<BookCopy> findById(Long aLong);

    Long countBookCopyByTitle_IdAndAndStatus(Long titleId, String status);

    @Override
    BookCopy save(BookCopy bookCopy);

    @Override
    void deleteById(Long aLong);


}
