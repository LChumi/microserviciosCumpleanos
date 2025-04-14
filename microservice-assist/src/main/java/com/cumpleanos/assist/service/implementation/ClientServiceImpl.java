package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.service.http.HttpResponseHandler;
import com.cumpleanos.assist.service.http.IEmailClient;
import com.cumpleanos.assist.service.http.IModelsClient;
import com.cumpleanos.common.records.*;
import com.cumpleanos.core.models.entities.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ClientServiceImpl {

    private final IModelsClient modelsClient;
    private final IEmailClient emailClient;

    //TODO servicio de micorservicio models
    //USUARIOS
    public Usuario getUsuario(String usrId) {
        return HttpResponseHandler.handle(() -> modelsClient.findByUsrId(usrId),
                "Error al obtener el usuario con id: " + usrId);
    }

    public Usuario getUsuarioByCodigo(Long usrId) {
        return HttpResponseHandler.handle(() -> modelsClient.findByUsrCodigo(usrId),
                "Error al obtener el usuario con codigo: " + usrId);
    }

    public Usuario login(String usrId, String passwd) {
        return HttpResponseHandler.handle(() -> modelsClient.findByUsrClave(usrId, passwd),
                "Usuario invalido");
    }

    public Empleado getEmpledo(Long usuarioId) {
        return HttpResponseHandler.handle(() -> modelsClient.getEmpleadoByUsuarioId(usuarioId),
                "Error al obtener el empleado del usuario: " + usuarioId);
    }

    //PUNTO VENTA
    public Set<PuntoVentaDTO> listPuntoVentas(Long empresa, Long almacen) {
        return HttpResponseHandler.handle(() -> modelsClient.listarPve(empresa, almacen),
                "Error al obtener los puntos del almacen en la empresa" + empresa);
    }

    //DTIPODOC
    public DTipoDocDTO getTipoDoc(Long empresa, Long tpdCodigo) {
        return HttpResponseHandler.handle(() -> modelsClient.getDtipoDoc(empresa, tpdCodigo),
                "Error al obtener el tipo de document del empresa: " + empresa);
    }

    public Set<AlmacenDTO> listAlmacenes(Long empresa) {
        return HttpResponseHandler.handle(() -> modelsClient.listarAlmacenes(empresa),
                "Error al listar los almacenes en la empresa: " + empresa);
    }

    //DFACTURA
    public Boolean newDfactura(Dfactura d) {
        return HttpResponseHandler.handle(() -> modelsClient.create(d),
                "Error al crear el detalle de la factura en la empresa: " + d.getId().getEmpresa());
    }

    //CREPOSICION
    public Creposicion save(Creposicion c) {
        return HttpResponseHandler.handle(() -> modelsClient.saveCreposicionEc(c),
                "Error al guardar el creposicion en la empresa: " + c.getId().getEmpresa());
    }

    //CLIENTE
    public Cliente save(Cliente cliente) {
        return HttpResponseHandler.handle(() -> modelsClient.saveCliente(cliente),
                "Error al guardar el cliente en BD " + cliente.getId() + " ruc:" + cliente.getRucCedula());
    }

    public ClienteRecord getByRucAndEmpresa(String ruc, Short tipo, Long empresa){
        return HttpResponseHandler.handle(() -> modelsClient.findByRucAndEmpresa(ruc, tipo, empresa),
                "Error al obtener el cliente por Ruc: "+ruc+" Tipo: "+tipo +"Empresa: "+empresa);
    }

    public List<String> getIdsClientes(String cliId, Long empresa) {
        return HttpResponseHandler.handle(() -> modelsClient.getClientes(cliId, empresa),
                "Error al obtener los ids de clientes " + cliId);
    }

    //TODO servicio de microservicio notificacion
    public void enviarEmail(EmailRecord email) {
        try {
            ResponseEntity<?> response = emailClient.enviarEmail(email);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Error al enviar el email: " + response.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Error al enviar el email", e);
            throw new RuntimeException("Error al enviar el email: " + e.getMessage());
        }
    }
}
