package com.cumpleanos.pos.service.implementation;

import com.cumpleanos.common.exception.ApiResponse;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.pos.persistence.api.jep.JepRequestQr;
import com.cumpleanos.pos.persistence.api.jep.JepResponseQr;
import com.cumpleanos.pos.persistence.api.jep.NotificacionJep;
import com.cumpleanos.pos.persistence.entity.ReciboPOSView;
import com.cumpleanos.pos.persistence.repository.ReciboPOSRepository;
import com.cumpleanos.pos.persistence.repository.ReciboPOSViewRepositorio;
import com.cumpleanos.pos.service.interfaces.IJepFasterSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@PropertySource("classpath:api-keys.properties")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JepFasterSyncServiceImpl implements IJepFasterSyncService {

    private final ReciboPOSRepository reciboPOSRepository;
    private final ReciboPOSViewRepositorio viewRepositorio;
    private final ModelsClientServiceImpl modelsClientService;
    private final JepFasterClientService jepFasterClientService;

    @Value("${usuario-jep}")
    private String usuarioJep;
    @Value("${password-jep}")
    private String passwordJep;
    @Value("${cod-institucion-jep}")
    private String codigoInstitucion;

    @Override
    public JepResponseQr generarQR(Long usrLiquida, Long empresa) {
        ReciboPOSView view = viewRepositorio.findByUsrLiquidaAndEmpresa(usrLiquida, empresa).orElseThrow(() ->
                new RuntimeException("Recibo no encontrado")
        );
        return null;
    }

    @Override
    public ServiceResponse validarPago(Long usrLiquida, Long empresa) {
        ReciboPOSView view = viewRepositorio.findByUsrLiquidaAndEmpresa(usrLiquida, empresa).orElseThrow(() ->
                new RuntimeException("Recibo no encontrado")
        );
        return null;
    }

    @Override
    public ServiceResponse procesarPago(NotificacionJep notificacion) {
        log.info(notificacion.toString());
        return null;
    }

    private JepRequestQr createRequest(ReciboPOSView v) {
        ApiResponse<Sistema> empresa = modelsClientService.getEmpresa(v.getEmpresa());
        if (empresa.getData() == null) {
            throw new RuntimeException("Error al obtener la empresa");
        }

        return new JepRequestQr(
                usuarioJep,
                passwordJep,
                String.valueOf(v.getTotal()),
                codigoInstitucion,
                null,
                "Cuenca",
                "0",
                "",
                "",
                ""
        );

    }
}
