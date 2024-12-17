package com.cumpleanos.pos.service.exception;

import com.cumpleanos.core.models.exception.ApiResponse;
import com.cumpleanos.core.models.exception.ErrorResponse;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.function.Supplier;

@Slf4j
public class HttpResponseHandler {

    public static <T> ApiResponse<T> handle(Supplier<ResponseEntity<T>> supplier, String errorMessage){
        try {
            T body = supplier.get().getBody();
            return new ApiResponse<>(body);
        } catch (FeignException e) {
            log.error("Error de Feign: {} - {} ", e.status(), e.getMessage());
            return new ApiResponse<>(new ErrorResponse(e.status(), e.getMessage()));
        } catch (Exception e) {
            log.error(errorMessage, e);
            return new ApiResponse<>(new ErrorResponse(500, e.getMessage()));
        }
    }
}
