package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.dto.ComprobanteDetalleProductoDTO;

import java.math.BigInteger;

public interface IComprobanteDetalleProductoService {
    ComprobanteDetalleProductoDTO getComprobanteDetalleProducto(BigInteger cco);
}
