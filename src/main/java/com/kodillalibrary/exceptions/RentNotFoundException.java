package com.kodillalibrary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RentNotFoundException extends RuntimeException {

    public RentNotFoundException(Long rentId) {
        super("Rent with id " + rentId + " not found");
    }

}