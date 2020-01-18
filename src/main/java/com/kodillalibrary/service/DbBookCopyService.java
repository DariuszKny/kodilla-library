package com.kodillalibrary.service;

import com.kodillalibrary.controller.UserNotFoundExcepion;
import com.kodillalibrary.domain.BookCopy;
import com.kodillalibrary.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DbBookCopyService {
    @Autowired
    public BookCopyRepository bookCopyRepository;


    public BookCopy saveBookCopy(final BookCopy bookCopy){
        return bookCopyRepository.save(bookCopy);
    }

    public BookCopy getBookCopyById(final Long aLong) throws UserNotFoundExcepion {
        return bookCopyRepository.findById(aLong).orElseThrow(() -> new UserNotFoundExcepion());
    }

    public void deleteBookCopy(final Long aLong){
        bookCopyRepository.deleteById(aLong);
    }

    public long getCount(Long titleId){
       return bookCopyRepository.countBookCopyByTitle_IdAndAndStatus(titleId,"Available");
    }

    public BookCopy changeStatus(Long bookCopyId) throws UserNotFoundExcepion {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(() -> new UserNotFoundExcepion());
        if (bookCopy.getStatus().equals("Available")) {
            bookCopy.setStatus("Rented");
        } else {
            bookCopy.setStatus("Available");
        }
        return bookCopy;
    }
}
