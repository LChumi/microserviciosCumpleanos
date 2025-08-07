package com.cumpleanos.mongo.presentation.controller;

import com.cumpleanos.mongo.persistence.models.mails.ContactAddress;
import com.cumpleanos.mongo.persistence.models.mails.EmailGroups;
import com.cumpleanos.mongo.service.interfaces.IEmailGroupsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mongo")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Grupo Correos", description = "Documentacion API Gestor Grupo correos")
public class EmailGroupsController {
    
    private final IEmailGroupsService service;

    @Operation(summary = "Obtener grupo", description = "Obtener Lista de correos por tipo", tags = {"Grupo Correos"})
    @Parameter(name = "tipo", description = "Codigo de tipo")
    @GetMapping("/email/tipo/{tipo}")
    public ResponseEntity<EmailGroups> getByTipo(@PathVariable("tipo") Long tipo) {
        EmailGroups emails = service.getByTipo(tipo);
        return ResponseEntity.ok(emails);
    }

    @Operation(summary = "Crear Grupo", description = "Crea nyuevo Grupo de correos", tags = {"Grupo Correos"})
    @PostMapping("/email-group/save")
    public ResponseEntity<EmailGroups> save(@RequestBody EmailGroups emails) {
        return ResponseEntity.ok(service.save(emails));
    }

    @Operation(summary = "Agregar correo", description = "Agrega nuevo correo al grupo", tags = {"Grupo Correos"})
    @Parameters({
            @Parameter(name = "Tipo", description = "Codigo del grupo"),
            @Parameter(name = "Correo", description = "Correo a agregar")
    })
    @PutMapping("/email/add/addressee/{tipo}")
    public ResponseEntity<EmailGroups> addAddressee(@PathVariable Long tipo, @RequestBody ContactAddress addressee) {
        EmailGroups emails = service.addAddressee(tipo, addressee);
        return ResponseEntity.ok(emails);
    }

    @Operation(summary = "Eliminar un correo", description = "Elimina un correo del grupo", tags = {"Grupo Correos"})
    @Parameters({
            @Parameter(name = "Tipo", description = "Codigo del grupo"),
            @Parameter(name = "Correo", description = "Correo a eliminar")
    })
    @DeleteMapping("/email/remove/{tipo}/{addressee}")
    public ResponseEntity<EmailGroups> removeAddressee(@PathVariable Long tipo, @PathVariable String addressee) {
        EmailGroups email = service.removeAddressee(tipo, addressee);
        return ResponseEntity.ok(email);
    }
}
