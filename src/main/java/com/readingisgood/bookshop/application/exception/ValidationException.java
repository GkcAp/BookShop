package com.readingisgood.bookshop.application.exception;


import com.readingisgood.bookshop.domain.exception.BusinessException;

public class ValidationException extends BusinessException {

    public ValidationException(String key) {
        super(key);
    }

}
