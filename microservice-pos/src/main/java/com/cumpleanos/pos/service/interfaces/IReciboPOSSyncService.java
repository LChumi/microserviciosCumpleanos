package com.cumpleanos.pos.service.interfaces;

import java.util.Map;

public interface IReciboPOSSyncService {

    Map<String, String> listarPuertos(Long usrLiquida, Long empresa);

    String procesarPago(Long usrLiquida, Long empresa);

    String anularPago(Long usrLiquida, Long empresa);

    String procesarPagoLan(Long usrLiquida, Long empresa);

    String anularPagoLan(Long usrLiquida, Long empresa);
}
