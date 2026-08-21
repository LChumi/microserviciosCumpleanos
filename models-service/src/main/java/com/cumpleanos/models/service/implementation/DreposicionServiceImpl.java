package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.dtos.ProductoReposicionDTO;
import com.cumpleanos.common.records.DreposicionDTO;
import com.cumpleanos.common.records.RevisionProductoRequest;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.core.models.entities.Dreposicion;
import com.cumpleanos.core.models.entities.Producto;
import com.cumpleanos.core.models.entities.StockOptimo;
import com.cumpleanos.core.models.ids.DreposicionId;
import com.cumpleanos.core.models.views.InvProdinfgenWebV;
import com.cumpleanos.models.persistence.repository.CreposicionRepository;
import com.cumpleanos.models.persistence.repository.DreposicionRepository;
import com.cumpleanos.models.persistence.repository.ProductoRepository;
import com.cumpleanos.models.persistence.repository.StockOptimoRepository;
import com.cumpleanos.models.persistence.repository.views.InvProdinfgenWebVRespository;
import com.cumpleanos.models.service.interfaces.IDreposicionService;
import com.cumpleanos.models.utils.enums.Sequence;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DreposicionServiceImpl extends GenericServiceImpl<Dreposicion, DreposicionId> implements IDreposicionService {

    private final DreposicionRepository repository;
    private final ProductoRepository productoRepository;
    private final CreposicionRepository creposicionRepository;
    private final InvProdinfgenWebVRespository invProdRepository;
    private final StockOptimoRepository stockOptimoRepository;

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

    //Productos reposicion
    @Override
    public List<ProductoReposicionDTO> getProductosByCreposicion(Long creposicion) {

        Creposicion c = creposicionRepository.findById_Codigo(creposicion);

        List<Dreposicion> drepo = repository.findByCreposicionId(creposicion);

        if (drepo.isEmpty()) {
            return List.of();
        }

        return construirLista(drepo, c.getId().getEmpresa(), c.getBodegaId());
    }

    @Override
    public List<ProductoReposicionDTO> getProductosByUsrLiquida(Long usrLiquida) {

        List<Dreposicion> drepo= repository.findByCreposicion_UsrLiquida(usrLiquida);

        if (drepo.isEmpty()){return List.of();}

        Dreposicion primera = drepo.getFirst();

        return construirLista(drepo, primera.getId().getEmpresa(), primera.getCreposicion().getBodegaId());
    }

    @Transactional
    @Override
    public ServiceResponse updateProductoReposicion(
            Long creposicion,
            Long productoId,
            Long cantidad,
            Long gondola
    ) {
        Dreposicion d = repository.findByCreposicionIdAndProductoId(creposicion, productoId);

        if (d == null) {
            throw new EntityNotFoundException("Producto no encontrado");
        }

        if (cantidad != null) {
            if (cantidad < 0) {
                throw new IllegalArgumentException("La cantidad no puede ser negativa");
            }
            d.setCantApr(cantidad);
        }

        if (gondola != null) {
            d.setGondolaId(gondola);
        }

        repository.save(d);

        return new ServiceResponse("Producto actualizado", true);
    }

    private List<ProductoReposicionDTO> construirLista(List<Dreposicion> drepo, Long empresa, Long bodega) {
        List<Long> productoIds = drepo.stream()
                .map(d-> d.getProducto().getId().getCodigo())
                .distinct()
                .toList();

        //Informacion de inventarios de todos los productos en una sola consulta
        List<InvProdinfgenWebV> productos= invProdRepository.findByProEmpresaAndBodCodigoAndProCodigoIn(empresa, bodega, productoIds);

        Map<Long, InvProdinfgenWebV> productoMap = productos.stream()
                .collect(
                        Collectors.toMap(
                                InvProdinfgenWebV::getProCodigo,
                                Function.identity()
                        ));

        //Traer el stock optimo de todos los productos de la bodega en una sola consulta
        List<StockOptimo> stocks = stockOptimoRepository.findByBodegaAndProductoIn(bodega, productoIds);

        Map<Long, StockOptimo> stockMap = stocks.stream()
                .collect(Collectors.toMap(
                        StockOptimo::getProducto,
                        Function.identity()
                ));

        return drepo.stream()
                .map(d -> {
                    Long productoId = d.getProducto().getId().getCodigo();
                    InvProdinfgenWebV producto = productoMap.get(productoId);
                    //si el producto no existe en la lista de inventarios, se omite del resultado
                    if (producto == null) {
                        return null;
                    }
                    //stock optimo puede no existir para un producto
                    StockOptimo stock = stockMap.get(productoId);

                    return build(d, producto, stock);
                })
                .filter(Objects::nonNull)
                .toList();
    }

    //Productos-reposicion
    private ProductoReposicionDTO build(
            Dreposicion d,
            InvProdinfgenWebV i,
            StockOptimo st
    ) {
        return ProductoReposicionDTO.builder()
                .empresa(d.getId().getEmpresa())
                .id(d.getId().getCodigo())
                .creposicion(d.getCreposicionId())
                .codigoProducto(d.getProductoId())
                .descripcion(i.getProNombre())
                .observacion(d.getObservacion())
                .usuario(d.getUsuario())
                .gondola(
                        d.getDrpGondola() != null
                                ? d.getDrpGondola().getGonId()
                                : ""
                )
                .canSol(Math.toIntExact(d.getCantSol()))
                .canApr(Math.toIntExact(d.getCantApr()))
                .stock(i.getStockDisp())
                .transito(i.getCantPendIng())
                .codigoStock(st != null ? st.getId().getCodigo() : null)
                .min(st != null ? Math.toIntExact(st.getMinimo()) : null)
                .max(st != null ? Math.toIntExact(st.getMaximo()) : null)
                .build();
    }

    private long calcularNuevaCantidad(Long actual, Long cantidad, Boolean shouldAdd) {

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

    private String calcularObservacion(Long cantSol, Long cantApr) {

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
