package com.cumpleanos.models.service.functions;

import com.cumpleanos.models.repository.functions.FunctionOracleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FunctionOracleServiceImpl {

    private final FunctionOracleRepository function;

    public Long agenteParametro(int empresa, String sigla, String secuencia , String almacen, int tipo){
        return function.ejecutarAstGen(empresa, sigla, secuencia, almacen, tipo);
    }

    public Long verificarRucJuridico(String ruc){
        return function.verificarRucJuridico(ruc);
    }
}