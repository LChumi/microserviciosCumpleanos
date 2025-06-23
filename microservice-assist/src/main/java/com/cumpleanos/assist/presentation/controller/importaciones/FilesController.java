package com.cumpleanos.assist.presentation.controller.importaciones;

import com.cumpleanos.assist.persistence.dto.SolicitudRequestDTO;
import com.cumpleanos.assist.persistence.inmutables.SciResponse;
import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import com.cumpleanos.assist.service.implementation.files.FilesServicesImpl;
import com.cumpleanos.assist.service.interfaces.importaciones.ISolicitudImportacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class FilesController {

    private final FilesServicesImpl filesServices;
    private final ISolicitudImportacionService solicitudImportacionService;

    @PostMapping("/importaciones/excel/solicitud")
    public ResponseEntity<List<ProductImportTransformer>> importExcel(@RequestParam("file") MultipartFile file,
                                                                      @RequestParam("empresa") Long empresa) throws IOException {
        List<ProductImportTransformer> items = filesServices.readExcelFile(file, empresa);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/importaciones/confirmar/solicitud")
    public ResponseEntity<SciResponse> confirmarSolicitud(
            @RequestBody @Valid SolicitudRequestDTO solicitudRequestDTO) {
        log.info("Confirmar solicitud lista de items: {}", solicitudRequestDTO.getItems());

        SciResponse result = solicitudImportacionService.procesarSolicitud(solicitudRequestDTO);
        return ResponseEntity.ok(result);
    }

}
