package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.dtos.ComprobanteDetalleProductoDTO;
import com.cumpleanos.models.service.interfaces.IComprobanteDetalleProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ComprobanteDetalleController {

    private final IComprobanteDetalleProductoService solicitudCompraService;

    @GetMapping("comprobante-detalle/{cco}/productos")
    public ResponseEntity<ComprobanteDetalleProductoDTO> verSolicitudCompro(@PathVariable("cco") BigInteger cco) {
        ComprobanteDetalleProductoDTO response = solicitudCompraService.getComprobanteDetalleProducto(cco);
        return ResponseEntity.ok(response);
    }
}
