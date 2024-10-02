package com.cumpleanos.models.service;

import core.cumpleanos.models.entities.Autcliente;
import core.cumpleanos.models.ids.AutclienteId;

public interface IAutClienteService extends GenericService<Autcliente, AutclienteId>{

    Autcliente findByNroAutorizacion(String nroAutorizacion);
}
