package com.cumpleanos.models.presentation.advice;

import com.cumpleanos.common.exception.ErrorResponse;
import com.cumpleanos.models.service.exception.BadCredentialsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.awt.dnd.InvalidDnDOperationException;

@ControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e) {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {

        final HttpStatus notFound = HttpStatus.NOT_FOUND;
        final ErrorResponse error = new ErrorResponse(notFound.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return ResponseEntity.status(notFound).body(error);
    }

    @ExceptionHandler(InvalidDnDOperationException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDnDException(InvalidDnDOperationException e) {
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final ErrorResponse error = new ErrorResponse(badRequest.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return ResponseEntity.status(badRequest).body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
        final HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
        final ErrorResponse error = new ErrorResponse(unauthorized.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return ResponseEntity.status(unauthorized).body(error);
    }

}
