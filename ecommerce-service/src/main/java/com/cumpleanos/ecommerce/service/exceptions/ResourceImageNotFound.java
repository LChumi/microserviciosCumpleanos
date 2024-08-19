package com.cumpleanos.ecommerce.service.exceptions;

public class ResourceImageNotFound extends RuntimeException {

    public ResourceImageNotFound(String message) {
        super(message);
    }

    public ResourceImageNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
