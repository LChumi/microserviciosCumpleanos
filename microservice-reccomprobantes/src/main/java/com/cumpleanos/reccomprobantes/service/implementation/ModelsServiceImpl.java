package com.cumpleanos.reccomprobantes.service.implementation;

import com.cumpleanos.core.models.dto.EmailRecord;
import com.cumpleanos.core.models.entities.*;
import com.cumpleanos.reccomprobantes.service.http.EmailClient;
import com.cumpleanos.reccomprobantes.service.http.HttpResponseHandler;
import com.cumpleanos.reccomprobantes.service.http.ModelsClient;
import com.cumpleanos.reccomprobantes.service.http.SriNodeClient;
import com.cumpleanos.reccomprobantes.persistence.models.json.ComprobanteJson;
import com.cumpleanos.reccomprobantes.persistence.models.json.request.AutorizacionRequest;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ModelsServiceImpl{

    private final ModelsClient modelsClient;
    private final SriNodeClient sriNodeClient;
    private final EmailClient emailClient;

    //TODO servicio que viene del API Node del SRI
    public ComprobanteJson getComprobantesSri(String claveAcceso){
        return HttpResponseHandler.handle(() -> sriNodeClient.autorizacion(new AutorizacionRequest(claveAcceso, "2")),
                "Error al obtener el comprobante del SRI");
    }

    //TODO servicio que viene del controlador SistemaController
    public Sistema getEmpresaByRuc(String ruc){
        return HttpResponseHandler.handle(() -> modelsClient.findByRuc(ruc),
                "Error al obtener la empresa "+ ruc);
    }
    //TODO servicio que viene del controlador SriDocEleEmiServiceController
    public SriDocEleEmi getSriDocByClaveAcceso(String claveAcceso){
        return HttpResponseHandler.handle(() -> modelsClient.findByClaveAcceso(claveAcceso),
                "Error al obtener el documento "+ claveAcceso);
    }

    public SriDocEleEmi updateSriDoc(SriDocEleEmi sriDoc){
        return HttpResponseHandler.handle(() -> modelsClient.updateDocument(sriDoc),
                "Error al actualizar el documento en BD "+sriDoc.getId());
    }

    public SriDocEleEmi save(SriDocEleEmi sriDoc){
        return HttpResponseHandler.handle(() -> modelsClient.save(sriDoc),
                "Error al guardar el documento en BD "+sriDoc.getId());
    }

    //TODO servicio que viene del controlador ClienteController
    public Cliente getByRucAndEmpresa(String ruc, Short tipo, Long empresa){
        return HttpResponseHandler.handle(() -> modelsClient.findByRucAndEmpresa(ruc, tipo, empresa),
                "Error al obtener el cliente por Ruc: "+ruc+" Tipo: "+tipo +"Empresa: "+empresa);
    }

    public  Cliente save (Cliente cliente){
        return HttpResponseHandler.handle(() -> modelsClient.save(cliente),
                "Error al guardar el cliente en BD "+cliente.getId() + " ruc:" + cliente.getRucCedula());
    }

    public List<String> getIdsClientes(String cliId, Long empresa){
        return HttpResponseHandler.handle(() -> modelsClient.getClientes(cliId, empresa),
                "Error al obtener los ids de clientes "+ cliId);
    }

    //TODO servicio que viene de FunctionOracleController
    public Long verificarJuridico(String ruc){
        return HttpResponseHandler.handle(() -> modelsClient.verificarJuridico(ruc),
                "Error al verificar el tipo juridico del ruc: "+ruc);
    }

    public Long verificarParametro(Long empresa, String sigla, String secuencia, int tipo) {
        return HttpResponseHandler.handle(() -> modelsClient.verificarParametro(empresa, sigla, secuencia, tipo),
                "Error al verificar el parámetro, parametros empresa: "+empresa+" sigla: "+sigla+" secuencia: "+secuencia);
    }

    //TODO Servicio que viene del AutClienteController
    public Autcliente getAutCliente(String nroAut, Long empresa) {
        return HttpResponseHandler.handle(() -> modelsClient.getAutCliente(nroAut, empresa),
                "Error al obtener el Autcliente nroAut: "+nroAut+ " empresa: "+empresa);
    }

    public Autcliente saveAutCliente(Autcliente autor) {
        return HttpResponseHandler.handle(() -> modelsClient.saveAutCliente(autor),
                "Error al guardar el Autcliente "+autor.getId());
    }

    //TODO Servicio que viene de RetDatoController
    public RetDato getRetDato(Long empresa, Long tablacoa, String id) {
        return HttpResponseHandler.handle(() -> modelsClient.getRetDato(empresa, tablacoa, id),
                "Error al obtener el RetDato empresa:"+ empresa+ " tablacoa: "+tablacoa+" id: "+id);
    }

    //TODO Servicio que viene de EmailController
    public void enviarEmail(EmailRecord email) {
        try {
            ResponseEntity<?> response = emailClient.enviar(email);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Error al enviar el email " + response.getStatusCode());
            }
        } catch (FeignException e) {
            log.error("Error de comunicación con el servicio de notificaciones: {}", e.getMessage(), e);
            throw new RuntimeException("Error al enviar el email");
        }
    }
}