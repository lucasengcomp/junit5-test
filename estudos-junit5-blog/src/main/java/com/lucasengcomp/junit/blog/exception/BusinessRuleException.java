package com.lucasengcomp.junit.blog.exception;

public class BusinessRuleException extends RuntimeException {

    public BusinessRuleException() {
    }

    public BusinessRuleException(String message) {
        super(message);
    }
}
