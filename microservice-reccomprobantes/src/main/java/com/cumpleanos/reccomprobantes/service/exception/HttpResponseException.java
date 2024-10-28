package com.cumpleanos.reccomprobantes.service.exception;

public class HttpResponseException extends RuntimeException{
    public HttpResponseException(String message) {
        super(message);
    }

    public HttpResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
