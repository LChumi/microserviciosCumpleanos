package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.StockOptimo;
import com.cumpleanos.core.models.ids.StockOptimoId;
import com.cumpleanos.models.persistence.dto.MinMaxUpdateRequest;

public interface IStockOptimoService extends GenericService<StockOptimo, StockOptimoId> {

    ServiceResponse updateMinMax(MinMaxUpdateRequest request);
}
