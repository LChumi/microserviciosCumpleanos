package com.cumpleanos.assist.persistence.repository.views;

import com.cumpleanos.assist.persistence.views.ListCcomprobaV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ListCcomprobaVRepository extends JpaRepository<ListCcomprobaV, BigInteger> {
}
