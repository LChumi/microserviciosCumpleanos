package com.cumpleanos.assist.service.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IGenericService<T, ID extends Serializable> {
    public T save(T t);

    public T findById(ID id);

    public List<T> findAll();

    public void delete(ID id);
}
