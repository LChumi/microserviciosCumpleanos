package com.cumpleanos.pos.service.implementation;

import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.core.models.exception.ApiResponse;
import com.cumpleanos.pos.service.exception.HttpResponseHandler;
import com.cumpleanos.pos.service.http.IModelsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class ModelsClientServiceImpl {

    private final IModelsClient modelsClient;

    public ApiResponse<Sistema> getEmpresa(Long id) {
        return HttpResponseHandler.handle(() ->
                modelsClient.getEmpresaById(id),
                "Error al obtener empresa con id " + id
                );
    }
}
