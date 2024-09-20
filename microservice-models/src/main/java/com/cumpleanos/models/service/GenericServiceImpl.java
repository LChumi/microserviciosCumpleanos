package com.cumpleanos.models.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class GenericServiceImpl <T, ID extends Serializable> implements GenericService<T, ID> {

    public abstract CrudRepository<T, ID> getRepository();

    @Transactional
    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public T findById(ID id) {
        return getRepository().findById(id).orElseThrow(()-> new EntityNotFoundException("Entidad no encontrada"));
    }

    @Override
    public List<T> findAll() {
        return StreamSupport.stream(getRepository().findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }
}
