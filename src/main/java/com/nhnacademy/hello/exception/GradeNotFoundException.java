package com.nhnacademy.hello.exception;

public class GradeNotFoundException extends RuntimeException{
    public GradeNotFoundException(Long id) {
        super("Grade not found " + id);
    }
}
