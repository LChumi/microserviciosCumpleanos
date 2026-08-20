package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.core.models.entities.Dreposicion;
import com.cumpleanos.core.models.entities.ProductoGondola;
import com.cumpleanos.models.persistence.repository.CreposicionRepository;
import com.cumpleanos.models.persistence.repository.DreposicionRepository;
import com.cumpleanos.models.persistence.repository.ProductoGondolaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cumpleanos.models.utils.enums.Sequence.USRLIQUIDACODIGO;

@Service
@RequiredArgsConstructor
public class CreposicionLiquidaService {

    private final CreposicionRepository creposicionRepository;
    private final DreposicionRepository drepoRepository;
    private final ProductoGondolaRepository pgonRepository;
    private final CreposicionServiceImpl crepoService;

    @Transactional
    public Long generaUsrLiquidaWebBatch(Long empresa, List<Long> codigos) {

        //creposiciones del lote
        List<Creposicion> creposiciones = creposicionRepository.findById_CodigoIn(codigos);
        if (creposiciones.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron creposiciones para los códigos dados");
        }

        //NEXTVAL usrliquida para el lote de creposiciones
        Long usrLiquida = crepoService.getNextSequenceValue(USRLIQUIDACODIGO);

        //Asignar memoria
        creposiciones.forEach(c -> c.setUsrLiquida(usrLiquida));

        // creposicion -> bodega solo para las que requieren asignacion de gondola
        Map<Long, Long> bodegaPorCreposicion = creposiciones.stream()
                .filter(c -> Integer.valueOf(1).equals(c.getTipo()))
                .collect(Collectors.toMap(
                        c -> c.getId().getCodigo(),
                        Creposicion::getBodegaId)
                );

        if (!bodegaPorCreposicion.isEmpty()) {
            asignarGondolasBatch(empresa, bodegaPorCreposicion);
        }

        return usrLiquida;
    }

    private void asignarGondolasBatch(Long empresa, Map<Long, Long> bodegaPorCreposicion) {

        List<Long> codigosGondola = List.copyOf(bodegaPorCreposicion.keySet());

        List<Dreposicion> detalles = drepoRepository.findByCreposicionIdIn(codigosGondola).stream()
                .filter(d -> d.getDrpGondola() == null)
                .toList();

        if (detalles.isEmpty()) return;

        List<Long> productos = detalles.stream()
                .map(Dreposicion::getProductoId)
                .distinct()
                .toList();

        List<Long> bodegas = bodegaPorCreposicion.values().stream()
                .distinct()
                .toList();

        List<ProductoGondola> productoGondolas = pgonRepository.findById_EmpresaAndProductoInAndBodegaInAndPgoInactivoFalse(empresa, productos, bodegas);

        Map<Long, Map<Long, Long>> gondolaPorProductoYBodega = productoGondolas.stream()
                .collect(Collectors.groupingBy(
                        ProductoGondola::getProducto,
                        Collectors.toMap(
                                ProductoGondola::getBodega,
                                ProductoGondola::getGondola,
                                (a, b) -> a
                        )
                ));

        for (Dreposicion d : detalles) {
            Long bodega = bodegaPorCreposicion.get(d.getCreposicionId());
            Long gondola = gondolaPorProductoYBodega
                    .getOrDefault(d.getProductoId(), Collections.emptyMap())
                    .get(bodega);
            d.setGondolaId(gondola);
        }
    }
}
