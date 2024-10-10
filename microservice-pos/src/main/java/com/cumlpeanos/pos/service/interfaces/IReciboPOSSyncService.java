package com.cumlpeanos.pos.service.interfaces;

import java.util.Map;

public interface IReciboPOSSyncService {

    String procesarPago(Long usrLiquida, Long empresa);
    Map<String,String> listarPuertos(Long usrLiquida, Long empresa);
    String anularPago(Long usrLiquida, Long empresa);
}
