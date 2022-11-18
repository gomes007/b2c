package com.pg.b2c.exceptions.impl;

import com.pg.b2c.exceptions.WebException;
import org.springframework.http.HttpStatus;

public class InternalServerException extends WebException {
    public InternalServerException(final String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
