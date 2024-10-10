package com.cumpleanos.pos.service.interfaces;

import com.cumpleanos.pos.persistence.entity.ReciboPOS;
import com.cumpleanos.pos.persistence.ids.ReciboPOSId;

import java.math.BigInteger;

public interface IReciboPOSService {
    ReciboPOS findByIdAndEmpresa(Long id, Long empresa);
    ReciboPOS findByCcoComproba(BigInteger ccoComproba);
    ReciboPOS getReciboPOS(ReciboPOSId id);
}
