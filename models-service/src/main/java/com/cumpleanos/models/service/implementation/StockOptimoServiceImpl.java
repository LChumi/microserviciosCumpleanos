package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.StockOptimo;
import com.cumpleanos.core.models.ids.StockOptimoId;
import com.cumpleanos.models.persistence.dto.MinMaxUpdateRequest;
import com.cumpleanos.models.persistence.repository.StockOptimoRepository;
import com.cumpleanos.models.service.interfaces.IStockOptimoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import static com.cumpleanos.models.utils.enums.Sequence.STOCKOPTIMOCODIGO;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockOptimoServiceImpl extends GenericServiceImpl<StockOptimo, StockOptimoId> implements IStockOptimoService {

    private final StockOptimoRepository repository;

    @Override
    public CrudRepository<StockOptimo, StockOptimoId> getRepository() {
        return repository;
    }

    @Override
    public StockOptimo save(StockOptimo entity) {
        Long codigo = getNextSequenceValue(STOCKOPTIMOCODIGO);
        StockOptimoId id = new StockOptimoId();

        id.setCodigo(codigo);
        id.setEmpresa(entity.getId().getEmpresa());
        entity.setId(id);

        return super.save(entity);
    }

    @Override
    public ServiceResponse updateMinMax(MinMaxUpdateRequest request) {
        StockOptimoId id = new StockOptimoId();
        id.setCodigo(request.codigo());
        id.setEmpresa(request.empresa());
        StockOptimo stock = repository.getReferenceById(id);
        stock.setMinimo(request.minimo());
        stock.setMaximo(request.maximo());
        repository.save(stock);
        return new ServiceResponse("Stock actualizado correctamente", true);
    }
}