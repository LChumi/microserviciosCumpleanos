package com.cumpleanos.assist.service.exception;

public class FileProcessingException extends RuntimeException{

    public FileProcessingException(String msg) {
        super(msg);
    }

    public FileProcessingException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
