package com.readingisgood.bookshop.infrastructure.exception;

public class SocketTimeoutFallBackException extends RuntimeException{
    public SocketTimeoutFallBackException(String message, Throwable cause) {
        super(message, cause);
    }
}
