package com.abc.exception;

import lombok.Data;

@Data
public class StudentException extends RuntimeException {
    private String field;
    private String value;

    public StudentException() {
        super();
    }

    public StudentException(String field, String value, String message) {
        super(message);
        this.field = field;
        this.value = value;
    }
}
