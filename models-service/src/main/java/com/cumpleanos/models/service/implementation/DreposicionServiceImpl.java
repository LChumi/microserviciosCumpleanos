package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.records.DreposicionDTO;
import com.cumpleanos.common.records.RevisionProductoRequest;
import com.cumpleanos.core.models.entities.Dreposicion;
import com.cumpleanos.core.models.entities.Producto;
import com.cumpleanos.core.models.ids.DreposicionId;
import com.cumpleanos.models.persistence.repository.DreposicionRepository;
import com.cumpleanos.models.persistence.repository.ProductoRepository;
import com.cumpleanos.models.service.interfaces.IDreposicionService;
import com.cumpleanos.models.utils.enums.Sequence;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DreposicionServiceImpl extends GenericServiceImpl<Dreposicion, DreposicionId> implements IDreposicionService {

    private final DreposicionRepository repository;
    private final ProductoRepository productoRepository;

    @Override
    public CrudRepository<Dreposicion, DreposicionId> getRepository() {
        return repository;
    }

    @Override
    public DreposicionDTO saveDetail(Dreposicion entity) {
        Long codigo = getNextSequenceValue(Sequence.DREPOSICIONCODIGO);

        DreposicionId id = new DreposicionId();
        id.setCodigo(codigo);
        id.setEmpresa(entity.getId().getEmpresa());
        entity.setId(id);
        return build(super.save(entity));
    }

    @Override
    public List<DreposicionDTO> getProductsByCreposicion(Long creposicion) {
        List<Dreposicion> drepo = repository.findByCreposicionId(creposicion);
        return drepo.stream().map(this::build).toList();
    }

    @Override
    public DreposicionDTO quantityAddedPerCreposicionAndProduct(RevisionProductoRequest r) {

        Dreposicion d = repository
                .findByCreposicionIdAndId_EmpresaAndProducto_ProId(
                        r.creposicion(),
                        r.empresa(),
                        r.barra());

        if (d == null) {
            return null;
        }

        long nuevaCantidad = calcularNuevaCantidad(d.getCantApr(), r.cantidad(), r.shouldAdd());

        d.setCantApr(nuevaCantidad);

        d.setUsuario(r.usuario());

        d.setObservacion(calcularObservacion(d.getCantSol(), nuevaCantidad));

        Dreposicion actualizado = repository.save(d);

        return build(actualizado);
    }

    @Override
    public DreposicionDTO saveDetailByProId(RevisionProductoRequest r) {
        Producto prod = productoRepository.findById_EmpresaAndProId(r.empresa(), r.barra()).orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        Long codigo = getNextSequenceValue(Sequence.DREPOSICIONCODIGO);

        DreposicionId id = new DreposicionId();
        id.setCodigo(codigo);
        id.setEmpresa(r.empresa());

        Dreposicion d = new Dreposicion();
        d.setId(id);
        d.setObservacion("PRODUCTO NO REGISTRADO EN LA LISTA ORIGNAL");
        d.setProductoId(prod.getId().getCodigo());
        d.setCantSol(0L);
        d.setCantApr(1L);
        d.setUsuario(r.usuario());
        d.setPrecio(prod.getPrecio2());
        d.setCreposicionId(r.creposicion());
        d.setProducto(prod);

        Dreposicion guardado = repository.save(d);
        return build(guardado);
    }

    private long calcularNuevaCantidad(
            Long actual,
            Long cantidad,
            Boolean shouldAdd) {

        long cantidadActual = actual != null
                ? actual
                : 0L;

        // si viene cantidad explícita -> reemplazar
        if (cantidad != null) {
            return Math.max(0L, cantidad);
        }

        // si no viene cantidad -> operar +/-1
        if (Boolean.TRUE.equals(shouldAdd)) {
            return cantidadActual + 1;
        }

        return Math.max(0L, cantidadActual - 1);
    }

    private String calcularObservacion(
            Long cantSol,
            Long cantApr) {

        long solicitada = cantSol != null
                ? cantSol
                : 0L;

        long aprobada = cantApr != null
                ? cantApr
                : 0L;

        if (aprobada == solicitada) {
            return "COMPLETO";
        }

        if (aprobada < solicitada) {
            return "FALTANTE";
        }

        return "SOBRANTE";
    }

    private DreposicionDTO build(Dreposicion d) {
        String nombre = d.getProducto() != null ? d.getProducto().getNombre() : null;
        String proId = d.getProducto() != null ? d.getProducto().getProId() : null;
        return new DreposicionDTO(
                d.getId().getCodigo(),
                d.getId().getEmpresa(),
                d.getCantSol(),
                d.getCantApr(),
                d.getObservacion(),
                d.getModFecha(),
                d.getUsuario(),
                d.getPrecio(),
                d.getPorcDesc(),
                d.getValDesc(),
                d.getTotal(),
                d.getCantDisp(),
                d.getProductoId(),
                nombre,
                proId
        );
    }
}
