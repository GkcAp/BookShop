package com.readingisgood.bookshop.infrastructure.exception;


import com.readingisgood.bookshop.domain.exception.BusinessException;

public class InfrastructureException extends BusinessException {
    public InfrastructureException(String key) {
        super(key);
    }

    public InfrastructureException(String key, String... args) {
        super(key, args);
    }

    public InfrastructureException(String message, Throwable cause, String key, String[] args) {
        super(message, cause, key, args);
    }
}
