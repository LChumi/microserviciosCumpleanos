package com.cumpleanos.reccomprobantes.service.implementation;

import com.cumpleanos.common.records.CompanyParametersRecord;
import com.cumpleanos.reccomprobantes.service.http.HttpResponseHandler;
import com.cumpleanos.reccomprobantes.service.http.MongoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MongoClientServiceImpl {

    private final MongoClient mongoClient;

    public CompanyParametersRecord getCompanyParameters(Long companyId){
        return HttpResponseHandler.handle(() -> mongoClient.getByCompanyId(companyId),
                "Error al obtener los parametros de la empresa: "+companyId);
    }
}
