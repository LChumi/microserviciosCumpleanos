package com.cumpleanos.pos.service.exception;

public class InfoPaymentException extends RuntimeException{
    public InfoPaymentException(String message) {
        super(message);
    }
    public InfoPaymentException(String message, Throwable cause) {
        super(message, cause);
    }
}
