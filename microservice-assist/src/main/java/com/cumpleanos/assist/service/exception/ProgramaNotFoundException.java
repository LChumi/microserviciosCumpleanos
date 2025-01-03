package com.cumpleanos.assist.service.exception;

public class ProgramaNotFoundException extends RuntimeException{
    public ProgramaNotFoundException(String message) {
        super(message);
    }
    public ProgramaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
