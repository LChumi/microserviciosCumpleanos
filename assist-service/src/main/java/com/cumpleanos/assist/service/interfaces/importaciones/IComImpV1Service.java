package com.cumpleanos.assist.service.interfaces.importaciones;

import com.cumpleanos.assist.persistence.views.ComImpV1;
import com.cumpleanos.assist.service.interfaces.IGenericService;

import java.math.BigInteger;
import java.util.List;

public interface IComImpV1Service extends IGenericService<ComImpV1, BigInteger> {

    List<ComImpV1> getImportaciones(Long empresa);
}