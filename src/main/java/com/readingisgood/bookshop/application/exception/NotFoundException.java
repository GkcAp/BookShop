package com.readingisgood.bookshop.application.exception;


import com.readingisgood.bookshop.domain.exception.BusinessException;

public class NotFoundException extends BusinessException {
    public NotFoundException(String key) {
        super(key);
    }

    public NotFoundException(String key, String... args) {
        super(key, args);
    }

    public NotFoundException(String message, Throwable cause, String key, String[] args) {
        super(message, cause, key, args);
    }
}
