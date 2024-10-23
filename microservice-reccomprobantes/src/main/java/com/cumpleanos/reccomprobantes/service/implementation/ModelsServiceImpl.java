package com.cumpleanos.reccomprobantes.service.implementation;

import com.cumpleanos.core.models.dto.EmailRecord;
import com.cumpleanos.core.models.entities.*;
import com.cumpleanos.reccomprobantes.service.http.EmailClient;
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
import org.springframework.web.client.HttpClientErrorException;

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
        try {
            ResponseEntity<ComprobanteJson> response = sriNodeClient.autorizacion(new AutorizacionRequest(claveAcceso, "2"));
            return response.getBody();
        } catch (HttpClientErrorException e){
            return null;
        }catch (Exception e){
            throw new RuntimeException("Error al obtener el documento");
        }
    }

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
    public Cliente getByRucAndEmpresa(String ruc, Short tipo, Long empresa){
        try{
            ResponseEntity<Cliente> response = modelsClient.findByRucAndEmpresa(ruc, tipo, empresa);
            return response.getBody();
        } catch (HttpClientErrorException e){
            return null;
        } catch (Exception e) {
            log.error("Error al obtener el documento mesage {}",e.getMessage(), e.getCause());
            throw new RuntimeException("Error al obtener el Proveedor por ruc y empresa ");
        }
    }

    public  Cliente save (Cliente cliente){
        try{
            ResponseEntity<Cliente> response = modelsClient.save(cliente);
            return response.getBody();
        } catch (HttpClientErrorException e){
            return null;
        } catch (Exception e){
            throw new RuntimeException("Error al guardar el Proveedor");
        }
    }

    public List<String> getIdsClientes(String cliId, Long empresa){
        try {
            ResponseEntity<List<String>> response = modelsClient.getClientes(cliId, empresa);
            return response.getBody();
        } catch (HttpClientErrorException e){
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los Ids de los Proveedores");
        }
    }

    //TODO servicio que viene de FunctionOracleController
    public Long verificarJuridico(String ruc){
        try {
            ResponseEntity<Long> response = modelsClient.verificarJuridico(ruc);
            return response.getBody();
        } catch (HttpClientErrorException e){
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error al verificar el juridico");
        }
    }

    public Long verificarParametro(Long empresa, String sigla, String secuencia, int tipo){
        try {
            ResponseEntity<Long> response = modelsClient.verificarParametro(empresa, sigla, secuencia, tipo);
            return response.getBody();
        } catch (HttpClientErrorException e){
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error al verificar el parametro");
        }
    }

    //TODO servicio que viene del AutClienteController
    public Autcliente getAutCliente(String nroAut, Long empresa){
        try {
            ResponseEntity<Autcliente> response = modelsClient.getAutCliente(nroAut, empresa);
            return response.getBody();
        } catch (HttpClientErrorException e){
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el Autcliente");
        }
    }

    public Autcliente saveAutCliente(Autcliente autor){
        try {
            ResponseEntity<Autcliente> response = modelsClient.saveAutCliente(autor);
            return response.getBody();
        } catch (HttpClientErrorException e){
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el Autcliente");
        }
    }

    //TODO servicio que viene de RetDatoController
    public RetDato getRetDato(Long empresa , Long tablacoa, String id){
        try{
            ResponseEntity<RetDato> response = modelsClient.getRetDato(empresa, tablacoa, id);
            return response.getBody();
        } catch (HttpClientErrorException e){
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el RetDato");
        }
    }

    //TODO servicio que viene de EmailController
    public void enviarEmail(EmailRecord email){
        try {
            ResponseEntity<Void> response = emailClient.enviar(email);
            if (!response.getStatusCode().is2xxSuccessful()){
                throw new RuntimeException("Error al enviar el email "+ response.getStatusCode());
            }
        }catch (FeignException e){
            log.error("Error de comunicacion con el servicio de notificaciones: {}", e.getMessage());
            throw new RuntimeException("Error al enviar el email");
        }
    }

}
