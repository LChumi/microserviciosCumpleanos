package com.cumpleanos.assist.presentation.advice;

import com.cumpleanos.assist.service.exception.BadCredentialsException;
import com.cumpleanos.core.models.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handelGeneralException(Exception e) {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
        final HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
        final ErrorResponse error = new ErrorResponse(unauthorized.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return ResponseEntity.status(unauthorized).body(error);
    }
}
