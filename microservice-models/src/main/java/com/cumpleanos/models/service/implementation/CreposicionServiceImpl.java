package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.core.models.ids.CreposicionId;
import com.cumpleanos.models.persistence.repository.CreposicionRepository;
import com.cumpleanos.models.service.interfaces.ICreposicionService;
import com.cumpleanos.models.utils.enums.Sequence;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CreposicionServiceImpl extends GenericServiceImpl<Creposicion, CreposicionId> implements ICreposicionService {

    private final CreposicionRepository repository;

    @Override
    public CrudRepository<Creposicion, CreposicionId> getRepository() {
        return repository;
    }

    @Override
    public Boolean existCreposicion(Long empresa, Long codigo) {
        CreposicionId id = new CreposicionId();
        id.setEmpresa(empresa);
        id.setCodigo(codigo);
        Creposicion c = repository.findById(id).orElse(null);
        return c != null;
    }

    @Override
    public Boolean existCreposicionByEmpresaAndReferencia(String referencia, Long empresa) {
        Creposicion c = repository.findByReferenciaAndId_Empresa(referencia, empresa);
        return c != null;
    }

    @Override
    public ServiceResponse finalizarPedido(Long empresa, Long codigo, Long usrLiquida,Integer estado) {
        CreposicionId id = new CreposicionId();
        id.setEmpresa(empresa);
        id.setCodigo(codigo);
        Creposicion c = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado: empresa=" + empresa + ", código=" + codigo));

        if (estado == 0) {
            return actualizarEstadoBasico(c, estado);
        }

        if (tipoPermitido(c.getTipo())) {
            return actualizarEstadoLiquida(c, estado, usrLiquida);
        }

        log.warn("Tipo de pedido no permitido para actualización - Tipo: {}", c.getTipo());
        return new ServiceResponse("El tipo de pedido no permite esta operación", false);
    }

    @Override
    public Creposicion save(Creposicion entity) {
        Long codigo = getNextSequenceValue(Sequence.CREPOSICIONCODIGO);
        CreposicionId id = new CreposicionId();
        id.setCodigo(codigo);
        id.setEmpresa(entity.getId().getEmpresa());
        entity.setId(id);

        return super.save(entity);
    }

    private boolean tipoPermitido(Integer tipo) {
        return tipo != null && Arrays.asList(3, 4, 5).contains(tipo);
    }

    private ServiceResponse actualizarEstadoBasico(Creposicion c, Integer estado) {
        c.setEstado(estado);
        repository.save(c);
        log.info("Estado actualizado a 0 para pedido: {}", c.getId().getCodigo());
        return new ServiceResponse("Estado del pedido actualizado a 0", true);
    }

    private ServiceResponse actualizarEstadoLiquida(Creposicion c, Integer estado, Long usrLiquida) {
        c.setEstado(estado);
        c.setUsrLiquida(usrLiquida);
        repository.save(c);
        log.info("Pedido actualizado con estado={} y usrLiquida={}", estado, usrLiquida);
        return new ServiceResponse("Pedido actualizado correctamente", true);
    }

}
