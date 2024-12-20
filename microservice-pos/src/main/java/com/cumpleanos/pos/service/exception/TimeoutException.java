package com.cumpleanos.pos.service.exception;

public class TimeoutException extends RuntimeException{
    public TimeoutException(String message) {
        super(message);
    }
    public TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
