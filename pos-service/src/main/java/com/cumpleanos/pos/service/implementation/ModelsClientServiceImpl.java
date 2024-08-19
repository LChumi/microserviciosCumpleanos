package com.cumpleanos.pos.service.implementation;

import com.cumpleanos.common.exception.ApiResponse;
import com.cumpleanos.common.records.AlmacenDTO;
import com.cumpleanos.core.models.entities.Sistema;
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

    public ApiResponse<AlmacenDTO> getAlmacen(Long empresa, Long alm) {
        return HttpResponseHandler.handle(() ->
                        modelsClient.getAlmacenByempresaAndAlmId(empresa, alm),
                "Error al obtener almacen con id " + alm
        );
    }

    public ApiResponse<String> getCliente(Long empresa, Long cliente) {
        return HttpResponseHandler.handle(() ->
                        modelsClient.getClienteCedById(empresa, cliente),
                "Error al obtener cliente con id " + cliente
        );
    }
}
