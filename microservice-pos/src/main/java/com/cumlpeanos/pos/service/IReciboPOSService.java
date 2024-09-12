package com.cumlpeanos.pos.service;

import com.cumlpeanos.pos.models.entity.ReciboPOS;
import com.cumlpeanos.pos.models.ids.ReciboPOSId;

import java.math.BigInteger;

public interface IReciboPOSService {
    ReciboPOS findByIdAndEmpresa(Long id, Long empresa);
    ReciboPOS findByCcoComproba(BigInteger ccoComproba);
    ReciboPOS getReciboPOS(ReciboPOSId id);
}
