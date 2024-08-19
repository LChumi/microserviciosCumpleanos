package com.cumpleanos.assist.service.http;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.function.Supplier;

@Slf4j
public class HttpResponseHandler {

    public static <T> T handle(Supplier<ResponseEntity<T>> supplier, String errorMessage) {

        try {

            ResponseEntity<T> response = supplier.get();
            return response != null ? response.getBody() : null;

        } catch (FeignException e) {

            log.error("Feign error {} - {}", e.status(), e.contentUTF8());
            return null;

        } catch (Exception e) {

            log.error(errorMessage, e);
            return null;

        }

    }

}