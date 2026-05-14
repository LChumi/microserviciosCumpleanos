package com.cumpleanos.assist.service.implementation.recepcion_almacenes;

import com.cumpleanos.assist.persistence.inmutables.ComprobantesCcoRequest;
import com.cumpleanos.assist.persistence.repository.views.PendingInvoiceHeaderViewRepository;
import com.cumpleanos.assist.persistence.repository.views.PendingInvoiceProductDetailViewRepository;
import com.cumpleanos.assist.service.exception.BusinessException;
import com.cumpleanos.assist.service.implementation.ClientServiceImpl;
import com.cumpleanos.common.dtos.BodegaDTO;
import com.cumpleanos.common.records.AlmacenDTO;
import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.core.models.entities.Dreposicion;
import com.cumpleanos.core.models.ids.CreposicionId;
import com.cumpleanos.core.models.views.FacRevprodWebV;
import com.cumpleanos.core.models.views.FacVerifiFacingWebV;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.cumpleanos.assist.utils.ReposicionAlmacenUtil.crearDetalle;
import static com.cumpleanos.assist.utils.ReposicionAlmacenUtil.generarCabeceraRevision;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecepcionAlmacenesServiceImpl {

    private final PendingInvoiceProductDetailViewRepository detailViewRepository;
    private final ClientServiceImpl modelsService;
    private final PendingInvoiceHeaderViewRepository headerViewRepository;

    public List<FacVerifiFacingWebV> getComprobantes() {
        return headerViewRepository.findAllByOrderByFechaFacDesc();
    }

    public List<FacVerifiFacingWebV> getComprobantesByEmpresa(Long empresa) {
        return headerViewRepository.findByEmpresaCompraOrderByFechaFacDesc(empresa);
    }

    public List<FacVerifiFacingWebV> getComprobantesByBodega(Long bodCodigo){
        return headerViewRepository.findByBodCodigo(bodCodigo);
    }

    //PRODUCTO DETALLE

    public List<FacRevprodWebV> detalleProductoPendientes(BigInteger cco) {
        return detailViewRepository.findByCcoCodigoOrderBySecuencia(cco);
    }

    public List<FacRevprodWebV> detalleProductoPendientesVariosComprobantes(ComprobantesCcoRequest request) {
        List<FacRevprodWebV> items = detailViewRepository.findByCcoCodigoIn(request.ccoCodigos());
        createRecepcionUpdateCco(request, items);
        return items;
    }

    private void createRecepcionUpdateCco(ComprobantesCcoRequest r, List<FacRevprodWebV> list) {
        validarEntradas(r, list);

        BodegaDTO bod = modelsService.getBodega(r.empresa(), r.bodega());
        AlmacenDTO alm = modelsService.getAlmacenDTO(bod.getAlmacen(), r.empresa());

        log.info("Iniciando proceso - Empresa: {}, Productos: {}, CCOs: {}",
                r.empresa(), list.size(), r.ccoCodigos().size());

        Creposicion cabecera = crearYGuardarCabecera(r, alm, bod);
        Long idCreposicion = cabecera.getId().getCodigo();

        List<Dreposicion> guardados = guardarDetalles(r.usuario(), list, idCreposicion);
        procesarCcos(r, idCreposicion, guardados);
    }

// ── Validación ───────────────────────────────────────────────────

    private void validarEntradas(ComprobantesCcoRequest r, List<FacRevprodWebV> list) {
        if (CollectionUtils.isEmpty(list))
            throw new IllegalArgumentException("Lista de productos no puede estar vacía");
        if (CollectionUtils.isEmpty(r.ccoCodigos()))
            throw new IllegalArgumentException("Lista de CCOs no puede estar vacía");
    }

// ── Cabecera ─────────────────────────────────────────────────────

    private Creposicion crearYGuardarCabecera(ComprobantesCcoRequest r, AlmacenDTO alm, BodegaDTO bod) {
        log.info("Creando cabecera Revision Mercaderia...");
        Creposicion cabecera = generarCabeceraRevision(r.empresa(), r.usuario(), alm.codigo(), bod.getId());

        return Optional.ofNullable(modelsService.saveCreposicion(cabecera))
                .orElseThrow(() -> {
                    log.error("No se pudo crear la cabecera Creposicion");
                    return new EntityNotFoundException("No se pudo crear el registro Revision Mercaderia");
                });
    }

// ── Detalles ─────────────────────────────────────────────────────

    private List<Dreposicion> guardarDetalles(String usuario, List<FacRevprodWebV> list, Long idCreposicion) {
        log.info("Guardando {} detalles...", list.size());

        List<String> errores  = new ArrayList<>();
        List<Dreposicion> guardados = new ArrayList<>();

        for (FacRevprodWebV producto : list) {
            try {
                Dreposicion d = modelsService.saveDreposicion(crearDetalle(usuario, producto, idCreposicion));
                if (d != null) {
                    guardados.add(d);
                    log.debug("Detalle guardado - producto: {}", producto.getProCodigo());
                } else {
                    errores.add(String.valueOf(producto.getProCodigo()));
                }
            } catch (Exception e) {
                log.error("Error guardando detalle producto {}: {}", producto.getProCodigo(), e.getMessage());
                errores.add(producto.getProCodigo() + " - " + e.getMessage());
            }
        }

        if (!errores.isEmpty()) {
            log.error("Fallaron {} detalles: {}", errores.size(), errores);
            compensarCreacion(idCreposicion, guardados);
            throw new BusinessException("No se pudieron guardar todos los detalles: " + String.join(", ", errores));
        }

        log.info("{} detalles guardados OK", guardados.size());
        return guardados;
    }

// ── CCOs ─────────────────────────────────────────────────────────

    private void procesarCcos(ComprobantesCcoRequest r, Long idCreposicion, List<Dreposicion> guardados) {
        log.info("Relacionando {} CCOs a Creposicion {}...", r.ccoCodigos().size(), idCreposicion);

        Map<Boolean, List<BigInteger>> resultado = r.ccoCodigos().stream()
                .collect(Collectors.partitioningBy(cco -> relacionarCco(cco, idCreposicion, r.empresa())));

        List<BigInteger> exitosos = resultado.get(true);
        List<BigInteger> fallidos  = resultado.get(false);

        if (exitosos.isEmpty()) {
            log.error("No se relacionó ningún CCO. Iniciando compensación...");
            compensarCreacion(idCreposicion, guardados);
            throw new BusinessException("No se pudo relacionar ningún CCO con Creposicion " + idCreposicion);
        }

        if (!fallidos.isEmpty()) {
            log.warn("CCOs parcialmente fallidos — exitosos: {}, fallidos: {} {}",
                    exitosos.size(), fallidos.size(), fallidos);
        }

        log.info("Proceso completado — detalles: {}, CCOs relacionados: {}/{}",
                guardados.size(), exitosos.size(), r.ccoCodigos().size());
    }

    private boolean relacionarCco(BigInteger cco, Long idCreposicion, Long empresa) {
        try {
            boolean resultado = Boolean.TRUE.equals(modelsService.updateCreposicion(cco, idCreposicion, empresa));
            if (resultado) log.debug("CCO {} relacionado OK", cco);
            else           log.warn("CCO {} respondió false", cco);
            return resultado;
        } catch (Exception e) {
            log.error("Error relacionando CCO {}: {}", cco, e.getMessage());
            return false;
        }
    }

// ── Compensación ─────────────────────────────────────────────────

    private void compensarCreacion(Long idCreposicion, List<Dreposicion> detallesGuardados) {
        log.warn("Iniciando compensación para Creposicion: {}", idCreposicion);

        detallesGuardados.forEach(detalle -> {
            try {
                modelsService.deleteDreposicion(detalle.getId());
                log.debug("Detalle eliminado: {}", detalle.getId());
            } catch (Exception e) {
                log.error("No se pudo eliminar detalle {}: {}", detalle.getId(), e.getMessage());
                marcarParaLimpiezaManual("detalle", detalle.getId());
            }
        });

        try {
            Long empresa = detallesGuardados.getFirst().getId().getEmpresa();
            CreposicionId id = new CreposicionId();
            id.setEmpresa(empresa);
            id.setCodigo(idCreposicion);
            modelsService.updateEstadoCreposicion(id);
            log.info("Cabecera {} marcada como FALLIDO", idCreposicion);
        } catch (Exception e) {
            log.error("No se pudo marcar cabecera {} como FALLIDO: {}", idCreposicion, e.getMessage());
            marcarParaLimpiezaManual("cabecera", idCreposicion);
        }
    }

    private void marcarParaLimpiezaManual(String tipo, Object id) {
        // TODO: enviar a tabla pending_cleanup o sistema de monitoreo
        log.error("PENDIENTE LIMPIEZA MANUAL — tipo: {}, id: {}", tipo, id);
    }

}