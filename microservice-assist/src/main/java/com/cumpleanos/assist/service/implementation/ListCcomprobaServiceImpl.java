package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.repository.views.ListCcomprobaVRepository;
import com.cumpleanos.assist.persistence.specification.ListCcomprobaVSpecification;
import com.cumpleanos.assist.persistence.views.ListCcomprobaV;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ListCcomprobaServiceImpl {

    private final ListCcomprobaVRepository repository;

    public Set<ListCcomprobaV> find(
            Long empresa,
            Short periodo,
            LocalDate fecha,
            Short mes,
            Long sigla,
            Long almacen,
            Long serie,
            Long numero) {

        Specification<ListCcomprobaV> spec = ListCcomprobaVSpecification.filterBy(empresa, periodo, fecha, mes, sigla, almacen, serie, numero);

        return new HashSet<>(repository.findAll(spec));
    }
}