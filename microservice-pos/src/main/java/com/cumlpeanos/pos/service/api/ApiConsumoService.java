package com.cumlpeanos.pos.service.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiConsumoService {

    private String baseSpringUrl;
    private final RestTemplate restTemplate;


}
