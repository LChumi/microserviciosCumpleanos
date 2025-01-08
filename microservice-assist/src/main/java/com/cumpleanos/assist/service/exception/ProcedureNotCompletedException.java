package com.cumpleanos.assist.service.exception;

public class ProcedureNotCompletedException extends RuntimeException {
    public ProcedureNotCompletedException(String message) {
        super(message);
    }
}
