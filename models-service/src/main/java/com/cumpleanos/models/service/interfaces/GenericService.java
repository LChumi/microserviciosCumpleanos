package com.cumpleanos.models.service.interfaces;

import java.io.Serializable;
import java.util.List;

public interface GenericService <T, ID extends Serializable> {

    T save(T entity);
    T findById(ID id);
    List<T> findAll();
    void delete(ID id);
}
