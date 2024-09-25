package com.cumpleanos.reccomprobantes.service;

import com.cumpleanos.reccomprobantes.clients.ModelsClient;
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
}
