package com.cumpleanos.models.service.implementation;

import com.cumpleanos.models.service.interfaces.GenericService;
import com.cumpleanos.models.utils.enums.Sequence;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class GenericServiceImpl <T, ID extends Serializable> implements GenericService<T, ID> {

    @PersistenceContext
    protected EntityManager em;

    public abstract CrudRepository<T, ID> getRepository();

    protected Long getNextSequenceValue(Sequence sequence) {
        String query = "SELECT " + sequence.getName() + ".NEXTVAL FROM DUAL";
        BigDecimal sequenceValue = (BigDecimal) em.createNativeQuery(query).getSingleResult();
        return (sequenceValue != null) ? sequenceValue.longValue() : null;
    }

    @Transactional
    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Transactional
    @Override
    public T findById(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public List<T> findAll() {
        return StreamSupport.stream(getRepository().findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }
}
