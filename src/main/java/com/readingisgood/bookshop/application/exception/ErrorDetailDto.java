package com.readingisgood.bookshop.application.exception;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ErrorDetailDto {

    private String key;
    private String message;
    private String errorCode;
    private String[] args;

    public ErrorDetailDto() {
        this.args = ArrayUtils.EMPTY_STRING_ARRAY;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String[] getArgs() {
        return this.args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public String toString() {
        return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("key", this.key).append("message", this.message).append("errorCode", this.errorCode).append("args", this.args).toString();
    }


}
