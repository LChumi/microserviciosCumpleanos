package com.cumpleanos.pos.service.implementation;

import com.cumpleanos.common.exception.ApiResponse;
import com.cumpleanos.common.records.AlmacenDTO;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.pos.persistence.api.jep.JepRequestQr;
import com.cumpleanos.pos.persistence.api.jep.JepResponseQr;
import com.cumpleanos.pos.persistence.api.jep.NotificacionJep;
import com.cumpleanos.pos.persistence.entity.ReciboPOS;
import com.cumpleanos.pos.persistence.entity.ReciboPOSView;
import com.cumpleanos.pos.persistence.ids.ReciboPOSId;
import com.cumpleanos.pos.persistence.repository.ReciboPOSRepository;
import com.cumpleanos.pos.persistence.repository.ReciboPOSViewRepositorio;
import com.cumpleanos.pos.service.exception.InfoPaymentException;
import com.cumpleanos.pos.service.exception.ReciboNotFoundException;
import com.cumpleanos.pos.service.interfaces.IJepFasterSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import static com.cumpleanos.pos.utils.DateUtils.*;
import static com.cumpleanos.pos.utils.StringUtils.getTransactionReference;

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

    @Override
    public JepResponseQr generarQR(Long usrLiquida, Long empresa) {
        ReciboPOSView view = viewRepositorio.findByUsrLiquidaAndEmpresa(usrLiquida, empresa).orElseThrow(() ->
                new RuntimeException("Recibo no encontrado")
        );
        JepRequestQr request = createRequest(view);
        ApiResponse<JepResponseQr> response = jepFasterClientService.getQR(request);
        if (response.getError() != null) {
            log.error("Error al generar QR: {}", response.getError());
            throw new InfoPaymentException("No se pudo generar el QR: " + response.getError());
        }
        actualizarReciboPosInicial(view, request.codigoTransaccion());
        return response.getData();
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
        ApiResponse<AlmacenDTO> almacen = modelsClientService.getAlmacen(v.getEmpresa(), v.getAlmacen());
        if (almacen.getData() == null) {
            throw new RuntimeException("Error al obtener la informacion de la sucursal");
        }

        String codigoTransaccion = v.getEmpresa() + v.getAlmId() + v.getPventa() + obtenerFechaHora();

        return new JepRequestQr(
                usuarioJep,
                passwordJep,
                String.valueOf(v.getTotal()),
                codigoTransaccion,
                v.getCapId(),
                null,
                almacen.getData().ciudad(),
                almacen.getData().direccion(),
                almacen.getData().nombre(),
                null
        );
    }

    private void actualizarReciboPosInicial(ReciboPOSView v, String codigoTransaccion){
        ReciboPOSId id = new ReciboPOSId(v.getRpoCodigo(), v.getEmpresa());
        ReciboPOS recibo = reciboPOSRepository.findById(id)
                .orElseThrow(() -> new ReciboNotFoundException("No se encontraron datos en la vista Recibo"));

        String transactionReference = getTransactionReference(v);

        recibo.setNroDoc(transactionReference);
        recibo.setReferencia(codigoTransaccion);
        recibo.setHora(obtenerHora());
        recibo.setFecha(obtenerFecha());

        reciboPOSRepository.save(recibo);
    }
}
