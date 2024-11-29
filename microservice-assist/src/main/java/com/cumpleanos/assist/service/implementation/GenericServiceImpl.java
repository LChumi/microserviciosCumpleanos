package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.service.interfaces.IGenericService;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class GenericServiceImpl<T, ID extends Serializable> implements IGenericService<T, ID> {

    public abstract CrudRepository<T, ID> getRepository();

    @Transactional
    @Override
    public T save(T t) {
        return getRepository().save(t);
    }

    @Transactional
    @Override
    public T findById(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public List<T> findAll() {
        return StreamSupport.stream(getRepository().findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }
}