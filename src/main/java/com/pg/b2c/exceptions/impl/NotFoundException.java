package com.pg.b2c.exceptions.impl;


import com.pg.b2c.exceptions.WebException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends WebException {
    public NotFoundException(final String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
