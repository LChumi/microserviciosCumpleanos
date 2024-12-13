package com.cumpleanos.assist.service.interfaces;

import com.cumpleanos.assist.persistence.views.ImpProdTrancitoVw;

import java.math.BigInteger;
import java.util.Set;

public interface IImpProdTrancitoVwService extends IGenericService<ImpProdTrancitoVw, BigInteger> {
    Set<ImpProdTrancitoVw> getImpProdTrancitoVwsByProdAndEmpresa(Long producto, Long empresa);
}
