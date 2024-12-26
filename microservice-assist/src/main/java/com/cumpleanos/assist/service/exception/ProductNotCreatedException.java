package com.cumpleanos.assist.service.exception;

public class ProductNotCreatedException extends RuntimeException {
    public ProductNotCreatedException(String message) {
        super(message);
    }
    public ProductNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
