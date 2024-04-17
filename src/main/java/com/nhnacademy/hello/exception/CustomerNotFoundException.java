package com.nhnacademy.hello.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long customerNo) {
        super("Customer not found " + customerNo);
    }
}
