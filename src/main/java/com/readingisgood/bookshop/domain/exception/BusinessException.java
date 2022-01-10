package com.readingisgood.bookshop.domain.exception;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;


public class BusinessException extends RuntimeException {
    private final String key;
    private final String[] args;

    public BusinessException(String key) {
        this.key = key;
        this.args = ArrayUtils.EMPTY_STRING_ARRAY;
    }

    public BusinessException(String key, String... args) {
        this.key = key;
        this.args = args;
    }

    public BusinessException(String message, Throwable cause, String key, String[] args) {
        super(message, cause);
        this.key = key;
        this.args = args;
    }

    public String getKey() {
        return this.key;
    }

    public String[] getArgs() {
        return this.args;
    }

    public String getMessage() {
        return "Business exception occurred " + this.key + " " + StringUtils.join(this.args);
    }
}
