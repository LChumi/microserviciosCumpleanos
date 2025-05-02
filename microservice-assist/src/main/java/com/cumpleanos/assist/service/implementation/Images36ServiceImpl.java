package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.service.http.HttpResponseHandler;
import com.cumpleanos.assist.service.http.Images36Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class Images36ServiceImpl {

    private final Images36Client client;

    public Resource getProductImage(String id){
        return HttpResponseHandler.handle(() -> client.getImageProduct(id),
                "Error al obtener la imagen ");
    }

    public Resource getUserImage(String id){
        return HttpResponseHandler.handle(() -> client.getImageUser(id),
                "Error al obtener la imagen ");
    }

    public Resource getLogoImage(String id){
        return HttpResponseHandler.handle(() -> client.getImageLogo(id),
                "Error al obtener la imagen ");
    }
}
