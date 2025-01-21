package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.dto.UpdateCcoBodObsRequest;
import com.cumpleanos.models.service.interfaces.ICcomprobaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CcomprobaController {
    private final ICcomprobaService service;

    @PutMapping("update/cco")
    public ResponseEntity<Boolean> updateCco(@RequestBody UpdateCcoBodObsRequest request) {
        Boolean result = service.updateBodegaCco(request.empresa(), request.codigo(), request.bodega(), request.observacion());
        return ResponseEntity.ok(result);
    }
}
