package com.cumpleanos.assist.service.exception;

public class UpdateEntityException extends RuntimeException {
    public UpdateEntityException(String message) {
        super(message);
    }

    public UpdateEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
