package com.kodillalibrary.service;

import com.kodillalibrary.domain.BookCopyStatus;
import com.kodillalibrary.exceptions.BookCopyException;
import com.kodillalibrary.exceptions.UserNotFoundException;
import com.kodillalibrary.domain.BookCopy;
import com.kodillalibrary.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookCopyService {
    @Autowired
    public BookCopyRepository bookCopyRepository;


    public BookCopy saveBookCopy(final BookCopy bookCopy){
        return bookCopyRepository.save(bookCopy);
    }

    public BookCopy getBookCopyById(final Long bookCopyId) {

        return bookCopyRepository
                .findById(bookCopyId)
                .orElseThrow(() -> new BookCopyException(bookCopyId));
    }

    public void deleteBookCopy(final Long aLong){
        bookCopyRepository.deleteById(aLong);
    }

    public long getCount(Long titleId){
       return bookCopyRepository.countBookCopyByTitle_IdAndAndStatus(titleId,"Available");
    }

    public void changeStatus(Long bookCopyId) throws UserNotFoundException {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(() -> new UserNotFoundException());

        if (bookCopy.getStatus() == BookCopyStatus.AVAILABLE) {
            bookCopy.setStatus(BookCopyStatus.AVAILABLE);
        } else {
            bookCopy.setStatus(BookCopyStatus.RENTED);
        }

        bookCopyRepository.save(bookCopy);
    }
}
