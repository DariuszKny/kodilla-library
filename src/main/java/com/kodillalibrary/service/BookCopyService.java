package com.kodillalibrary.service;

import com.kodillalibrary.domain.BookCopyDto;
import com.kodillalibrary.domain.BookCopyStatus;
import com.kodillalibrary.exceptions.BookCopyNotFoundException;
import com.kodillalibrary.exceptions.UserNotFoundException;
import com.kodillalibrary.domain.BookCopy;
import com.kodillalibrary.mapper.BookCopyMapper;
import com.kodillalibrary.repository.BookCopyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BookCopyService {
    private BookCopyRepository bookCopyRepository;
    private BookCopyMapper bookCopyMapper;

    public BookCopy saveBookCopy(final BookCopyDto bookCopyDto){
        return bookCopyRepository.save(bookCopyMapper.mapToBookCopy(bookCopyDto));
    }

    public BookCopy getBookCopyById(final Long bookCopyId) throws UserNotFoundException {
        return bookCopyRepository.findById(bookCopyId).orElseThrow(() -> new BookCopyNotFoundException(bookCopyId));
    }

    public void deleteBookCopy(final Long aLong){
        bookCopyRepository.deleteById(aLong);
    }

    public long getCount(Long titleId){
       return bookCopyRepository.countBookCopyByTitle_IdAndStatus(titleId,BookCopyStatus.AVAILABLE);
    }

    public void changeStatus(Long bookCopyId) throws BookCopyNotFoundException {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(() -> new BookCopyNotFoundException(bookCopyId));
        if (bookCopy.getStatus() == BookCopyStatus.AVAILABLE) {
            bookCopy.setStatus(BookCopyStatus.RENTED);
        } else {
            bookCopy.setStatus(BookCopyStatus.AVAILABLE);
        }
        saveBookCopy(bookCopyMapper.mapToBookCopyDto(bookCopy));
    }
}
