package com.cumpleanos.reccomprobantes.service;

import com.cumpleanos.reccomprobantes.clients.ModelsClient;
import com.cumpleanos.reccomprobantes.clients.SriNodeClient;
import com.cumpleanos.reccomprobantes.models.json.ComprobanteJson;
import com.cumpleanos.reccomprobantes.models.json.request.AutorizacionRequest;
import core.cumpleanos.models.entities.Cliente;
import core.cumpleanos.models.entities.Sistema;
import core.cumpleanos.models.entities.SriDocEleEmi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class ModelsServiceImpl{

    private final ModelsClient modelsClient;
    private final SriNodeClient sriNodeClient;

    //TODO servicio que viene del controlador SistemaController
    public Sistema getEmpresaByRuc(String ruc){
        try {
            ResponseEntity<Sistema> response = modelsClient.findByRuc(ruc);
            return response.getBody();
        }catch (HttpClientErrorException e){
            return null;
        }catch (Exception e){
            throw new RuntimeException("Error al obtener la empresa");
        }
    }
    //TODO servicio que viene del controlador SriDocEleEmiServiceController
    public SriDocEleEmi getSriDocByClaveAcceso(String claveAcceso){
        try {
            ResponseEntity<SriDocEleEmi> response = modelsClient.findByClaveAcceso(claveAcceso);
            return response.getBody();
        }catch (HttpClientErrorException e){
            return null;
        }catch (Exception e){
            throw new RuntimeException("Error al obtener el documento");
        }
    }

    public SriDocEleEmi updateSriDoc(SriDocEleEmi sriDoc){
        try {
            return modelsClient.updateDocument(sriDoc).getBody();
        }catch (HttpClientErrorException e){
            return null;
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el documento");
        }
    }

    public SriDocEleEmi save(SriDocEleEmi sriDoc){
        try {
            return modelsClient.save(sriDoc).getBody();
        }catch (HttpClientErrorException e){
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el docuemnto");
        }
    }

    //TODO servicio que viene del controlador ClienteController
    public Cliente getByRucAndEmpresa(String ruc, Long empresa){
        try{
            ResponseEntity<Cliente> response = modelsClient.findByRucAndEmpresa(ruc, empresa);
            return response.getBody();
        } catch (HttpClientErrorException e){
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el documento");
        }
    }

    //TODO servicio que viene del API Node del SRI
    public ComprobanteJson getComprobantesSri(String claveAcceso){
        try {
            ResponseEntity<ComprobanteJson> response = sriNodeClient.autorizacion(new AutorizacionRequest(claveAcceso, "2"));
            return response.getBody();
        } catch (HttpClientErrorException e){
            return null;
        }catch (Exception e){
            throw new RuntimeException("Error al obtener el documento");
        }
    }
}
