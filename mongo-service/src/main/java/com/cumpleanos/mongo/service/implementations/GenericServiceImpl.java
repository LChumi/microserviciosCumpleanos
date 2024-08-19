package com.cumpleanos.mongo.service.implementations;

import com.cumpleanos.mongo.service.interfaces.IGenericService;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class GenericServiceImpl<T, ID extends Serializable> implements IGenericService<T, ID> {

    public abstract CrudRepository<T, ID> getRepository();


    @Override
    public T save(T t) {
        return getRepository().save(t);
    }

    @Override
    public T findById(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public List<T> findAll() {
        return StreamSupport.stream(getRepository().findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }
}
