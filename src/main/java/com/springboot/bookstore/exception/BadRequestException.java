package com.springboot.bookstore.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException()
    {
        super("Could not process this request");
    }
}
