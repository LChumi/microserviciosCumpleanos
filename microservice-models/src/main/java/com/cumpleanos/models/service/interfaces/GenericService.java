package com.cumpleanos.models.service.interfaces;

import java.io.Serializable;
import java.util.List;

public interface GenericService <T, ID extends Serializable> {

    public T save(T entity);
    public T findById(ID id);
    public List<T> findAll();
    public void delete(ID id);
}
