package com.cumpleanos.pos.service.exception;

public class ReciboNotFoundException extends RuntimeException {
    public ReciboNotFoundException(String message) {
        super(message);
    }
    public ReciboNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
