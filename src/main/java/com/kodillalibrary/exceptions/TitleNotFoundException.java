package com.kodillalibrary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TitleNotFoundException extends RuntimeException {

    public TitleNotFoundException(Long titleId) {
        super("Title with id " + titleId + " not found");
    }
}
