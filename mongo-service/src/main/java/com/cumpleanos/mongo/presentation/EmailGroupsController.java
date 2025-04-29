package com.cumpleanos.mongo.presentation;

import com.cumpleanos.mongo.persistence.models.mails.ContactAddress;
import com.cumpleanos.mongo.persistence.models.mails.EmailGroups;
import com.cumpleanos.mongo.service.interfaces.IEmailGroupsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mongo")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EmailGroupsController {
    
    private final IEmailGroupsService service;

    @GetMapping("/email/tipo/{tipo}")
    public ResponseEntity<EmailGroups> getByTipo(@PathVariable("tipo") Long tipo) {
        EmailGroups emails = service.getByTipo(tipo);
        return ResponseEntity.ok(emails);
    }

    @PostMapping("/email-group/save")
    public ResponseEntity<EmailGroups> save(@RequestBody EmailGroups emails) {
        return ResponseEntity.ok(service.save(emails));
    }

    @PutMapping("/email/add/addressee/{tipo}")
    public ResponseEntity<EmailGroups> addAddressee(@PathVariable Long tipo, @RequestBody ContactAddress addressee) {
        EmailGroups emails = service.addAddressee(tipo, addressee);
        return ResponseEntity.ok(emails);
    }

    @DeleteMapping("/email/remove/{tipo}/{addressee}")
    public ResponseEntity<EmailGroups> removeAddressee(@PathVariable Long tipo, @PathVariable String addressee) {
        EmailGroups email = service.removeAddressee(tipo, addressee);
        return ResponseEntity.ok(email);
    }
}
