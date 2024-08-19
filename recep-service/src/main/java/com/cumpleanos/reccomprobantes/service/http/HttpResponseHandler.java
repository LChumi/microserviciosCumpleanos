package com.cumpleanos.reccomprobantes.service.http;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.function.Supplier;

@Slf4j
public class HttpResponseHandler {

    public static <T> T handle(Supplier<ResponseEntity<T>> supplier, String errorMessage){
        try{
            return supplier.get().getBody();
        }catch(FeignException e){
            log.error("Error de Feign: {} - {} ",e.status(), e.getMessage());
            return null;
        }catch(Exception e){
            log.error(errorMessage, e);
            return null;
        }
    }
}
