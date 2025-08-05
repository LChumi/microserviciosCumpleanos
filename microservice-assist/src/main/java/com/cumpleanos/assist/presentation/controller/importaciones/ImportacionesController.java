package com.cumpleanos.assist.presentation.controller.importaciones;

import com.cumpleanos.assist.persistence.dto.OrdenCompraListDTO;
import com.cumpleanos.assist.persistence.dto.SolicitudRequestDTO;
import com.cumpleanos.assist.persistence.inmutables.SciResponse;
import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import com.cumpleanos.assist.service.implementation.files.FilesServicesImpl;
import com.cumpleanos.assist.service.interfaces.importaciones.ISolicitudImportacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Importaciones" , description = "Funciones Modulo de importacion")
public class ImportacionesController {

    private final FilesServicesImpl filesServices;
    private final ISolicitudImportacionService solicitudImportacionService;

    @Operation(summary = "Carga de archivos para Solicitud de importaciones")
    @Parameter(name = "file", description = "Archivo excel", required = true)
    @Parameter(name = "empresa", description = "Codigo empresa", required = true)
    @PostMapping(value = "/importaciones/excel/solicitud", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<ProductImportTransformer>> importExcel(@RequestParam("file") MultipartFile file,
                                                                      @RequestParam("empresa") Long empresa) {
        List<ProductImportTransformer> items = filesServices.readExcelFile(file, empresa);
        return ResponseEntity.ok(items);
    }

    @Operation(summary = "Confirmacion de SCI carga al sistema")
    @Parameter(name = "requestDTO", description = "Dto de solicitud", required = true)
    @PostMapping("/importaciones/confirmar/solicitud")
    public ResponseEntity<SciResponse> confirmarSolicitud(
            @RequestBody @Valid SolicitudRequestDTO solicitudRequestDTO) {
        log.info("Confirmar solicitud lista de items: {}", solicitudRequestDTO.getItems());

        SciResponse result = solicitudImportacionService.procesarSolicitud(solicitudRequestDTO);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Carga de archivos Orden de compra")
    @Parameter(name = "file", description = "Archivo excel orden compra", required = true)
    @Parameter(name = "empresa", description = "Codigo de empresa", required = true)
    @Parameter(name = "prov", description = "Codigo proveedor", required = true)
    @PostMapping(value = "/importaciones/excel/orden_compra", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<OrdenCompraListDTO> transformOrdenCompra(@RequestParam("file") MultipartFile file,
                                                                   @RequestParam("empresa") Long empresa,
                                                                   @RequestParam("prov") Long prov) {
        OrdenCompraListDTO items = filesServices.getListSCi(file, empresa, prov);
        return ResponseEntity.ok(items);
    }

}
