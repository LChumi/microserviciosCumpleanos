package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.service.http.HttpResponseHandler;
import com.cumpleanos.assist.service.http.IndexNowClient;
import com.cumpleanos.assist.service.http.MongoClient;
import com.cumpleanos.common.dtos.IndexNowRequest;
import com.cumpleanos.common.records.ServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IndexNowService {

    private final IndexNowClient  indexNowClient;
    private final MongoClient  mongoClient;

    public ServiceResponse indexNow(String appName){
        IndexNowRequest response = HttpResponseHandler.handle(() -> mongoClient.getIndex(appName),
                "Error al obtener el index por App: " + appName);

        String responseIndex = HttpResponseHandler.handle(() -> indexNowClient.indexNow(response),
                "Error al enviar la indexacion" +  appName);

        if (response == null) {
            log.warn("No se pudo obtener el index para la app: {}", appName);
            return new ServiceResponse("No se pudo obtener el index para la app "+ appName, false);
        }

        return new ServiceResponse(
                responseIndex, true
        );
    }
}
