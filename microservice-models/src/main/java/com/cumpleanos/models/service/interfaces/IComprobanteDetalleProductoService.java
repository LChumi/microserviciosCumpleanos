package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.common.dtos.ComprobanteDetalleProductoDTO;

import java.math.BigInteger;

public interface IComprobanteDetalleProductoService {
    ComprobanteDetalleProductoDTO getComprobanteDetalleProducto(BigInteger cco);
}
