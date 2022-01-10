package com.readingisgood.bookshop.application.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Collections;
import java.util.List;

public class ErrorResponse {
    private long timestamp = System.currentTimeMillis();
    private String exception;
    private List<ErrorDetailDto> errors =  Collections.emptyList();

    public void setException(String exception) {
        this.exception = exception;
    }

    public List<ErrorDetailDto> getErrors() {
        return errors;
    }

    public void addError(ErrorDetailDto errorDetailDTO) {
        this.errors.add(errorDetailDTO);
    }

    public String toString() {
        return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("timestamp", this.timestamp).append("exception", this.exception).append("errors", this.errors).toString();
    }
}
