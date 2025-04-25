package com.cumpleanos.mongo.service.implementations;

import com.cumpleanos.mongo.persistence.models.ContactAddress;
import com.cumpleanos.mongo.persistence.models.EmailGroups;
import com.cumpleanos.mongo.persistence.repository.EmailGroupsRepository;
import com.cumpleanos.mongo.service.exceptions.DocumentNotFoundException;
import com.cumpleanos.mongo.service.interfaces.IEmailGroupsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EmailGroupsServiceImpl extends GenericServiceImpl<EmailGroups, String> implements IEmailGroupsService {
    
    private final EmailGroupsRepository repository;
    
    @Override
    public CrudRepository<EmailGroups, String> getRepository() {
        return repository;
    }

    @Override
    public EmailGroups getByTipo(Long tipo) {
        return repository.findByTipo(tipo).orElseThrow(() -> new DocumentNotFoundException("No se encontraron EmailGroups"));
    }

    @Override
    public EmailGroups addAddressee(Long tipo, ContactAddress addressee) {
        EmailGroups emails = repository.findByTipo(tipo).orElseThrow(() -> new DocumentNotFoundException("No se encontraron EmailGroups"));
        Set<String> existingDestinatarios = new HashSet<>();

        //Cargar direcciones existentes en un HashSet para verificar la unicidad
        emails.getDestinatarios().forEach(addressees -> existingDestinatarios.add(addressees.getDireccion()));

        if (existingDestinatarios.contains(addressee.getDireccion())) {
            throw  new RuntimeException("Destinatario existente");
        }

        emails.getDestinatarios().add(addressee);
        return repository.save(emails);
    }

    @Override
    public EmailGroups removeAddressee(Long tipo, String addressee) {
        EmailGroups emails = repository.findByTipo(tipo).orElseThrow(() -> new DocumentNotFoundException("No se encontraron EmailGroups"));

        //Buscar el destinatario en la lista y eliminarlo
        boolean removed = emails.getDestinatarios().removeIf(destinatario -> destinatario.getDireccion().equals(addressee));

        if (!removed) {
            throw  new RuntimeException("No se encontró Destinatario");
        }

        return repository.save(emails);
    }
}
