package com.cumpleanos.scheduler.service.implementation;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.common.records.StockEcommerceVDTO;
import com.cumpleanos.scheduler.service.http.AssistClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductUpdateService {

    private final AssistClient assistClient;

    public void updateProducts(StockEcommerceVDTO product) {
        try {
            ServiceResponse response = assistClient.getProductoUpdate(
                    product.producto(),
                    product.empresa(),
                    product.proId(),
                    0
            ).getBody();

            if (response != null && response.success()) {
                log.info("Producto actualizado satisfactoriamente SKU: {}", product.proId());
            } else if (response != null) {
                log.error("Error en la actualizacion de producto SKU: {} error: {}", product.proId(), response.message());
            } else {
                log.error("Error desconocido en SKU: {}", product.proId());
            }

            // PAUSA PARA NO SATURAR WORDPRESS / WOOCOMMERCE
            Thread.sleep(2000); // 2 segundos

        } catch (Exception e) {
            log.error("Excepcion al actualizar producto SKU: {} ERROR: {}", product.proId(), e.getMessage(), e);
        }
    }
}