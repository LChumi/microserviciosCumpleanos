package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.Autcliente;
import com.cumpleanos.core.models.ids.AutclienteId;

public interface IAutClienteService extends GenericService<Autcliente, AutclienteId>{

    Autcliente findByNroAutorizacion(String nroAutorizacion, Long empresa);
}
