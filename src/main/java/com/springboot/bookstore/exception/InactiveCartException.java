package com.springboot.bookstore.exception;

public class InactiveCartException extends RuntimeException {

    public InactiveCartException(Integer cartId)
    {
        super(String.format("Could not process with inactive cart %cart_id ",cartId));
    }
}
