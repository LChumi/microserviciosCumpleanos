package com.cumpleanos.models.service.implementation;

import com.cumpleanos.models.persistence.repository.functions.FunctionOracleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FunctionOracleServiceImpl {

    private final FunctionOracleRepository function;

    public Long agenteParametro(Long empresa, String sigla, String secuencia , String almacen, int tipo){
        return function.ejecutarAstGen(empresa, sigla, secuencia, almacen, tipo);
    }

    public Long verificarRucJuridico(String ruc){
        return function.verificarRucJuridico(ruc);
    }
}
