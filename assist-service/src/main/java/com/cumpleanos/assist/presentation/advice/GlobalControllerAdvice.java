package com.cumpleanos.assist.presentation.advice;

import com.cumpleanos.assist.service.exception.BadCredentialsException;
import com.cumpleanos.common.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex, HttpServletRequest request) {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        log.error("Error en la aplicación. Ruta: {} | Método: {} | IP: {} | User-Agent: {}",
                uri, method, ip, userAgent, ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ha ocurrido un error inesperado");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
        final HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
        final ErrorResponse error = new ErrorResponse(unauthorized.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return ResponseEntity.status(unauthorized).body(error);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleNoResource(NoResourceFoundException ex, HttpServletRequest request) {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        log.warn("Recurso no disponible. Ruta: {} | Método: {} | IP: {} | User-Agent: {}",
                uri, method, ip, userAgent, ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Recurso no disponible");
    }

}