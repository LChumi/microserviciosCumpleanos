package com.cumlpeanos.pos.service.interfaces;

import com.cumlpeanos.pos.persistence.entity.ReciboPOS;
import com.cumlpeanos.pos.persistence.ids.ReciboPOSId;

import java.math.BigInteger;

public interface IReciboPOSService {
    ReciboPOS findByIdAndEmpresa(Long id, Long empresa);
    ReciboPOS findByCcoComproba(BigInteger ccoComproba);
    ReciboPOS getReciboPOS(ReciboPOSId id);
}
