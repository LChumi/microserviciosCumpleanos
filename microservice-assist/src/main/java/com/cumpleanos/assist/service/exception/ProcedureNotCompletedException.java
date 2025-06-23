package com.cumpleanos.assist.service.exception;

public class ProcedureNotCompletedException extends RuntimeException {
    public ProcedureNotCompletedException(String message) {
        super(message);
    }

    public ProcedureNotCompletedException(String message, Throwable cause) {
        super(message, cause);
    }
}
