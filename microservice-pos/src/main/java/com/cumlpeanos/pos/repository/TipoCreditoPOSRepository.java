package com.cumlpeanos.pos.repository;

import com.cumlpeanos.pos.models.entity.TipoCreditoPOS;
import com.cumlpeanos.pos.models.ids.TipoCreditoPOSId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCreditoPOSRepository extends JpaRepository<TipoCreditoPOS, TipoCreditoPOSId> {

}
