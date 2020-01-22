package com.kodillalibrary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookCopyException extends RuntimeException {

    public BookCopyException(Long bookCopyId) {
        super("Book copy with id " + bookCopyId + " not found");
    }
}
