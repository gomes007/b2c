package com.pg.b2c.exceptions.impl;



import com.pg.b2c.exceptions.WebException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends WebException {
    public BadRequestException(final String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
