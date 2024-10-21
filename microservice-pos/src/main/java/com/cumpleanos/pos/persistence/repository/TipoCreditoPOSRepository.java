package com.cumpleanos.pos.persistence.repository;

import com.cumpleanos.pos.persistence.entity.TipoCreditoPOS;
import com.cumpleanos.pos.persistence.ids.TipoCreditoPOSId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCreditoPOSRepository extends JpaRepository<TipoCreditoPOS, TipoCreditoPOSId> {

}
