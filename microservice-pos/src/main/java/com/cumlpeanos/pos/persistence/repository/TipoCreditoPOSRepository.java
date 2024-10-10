package com.cumlpeanos.pos.persistence.repository;

import com.cumlpeanos.pos.persistence.entity.TipoCreditoPOS;
import com.cumlpeanos.pos.persistence.ids.TipoCreditoPOSId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCreditoPOSRepository extends JpaRepository<TipoCreditoPOS, TipoCreditoPOSId> {

}
