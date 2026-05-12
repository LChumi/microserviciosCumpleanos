package com.cumpleanos.assist.persistence.repository.views;

import com.cumpleanos.core.models.views.FacVerifiFacingWebV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PendingInvoiceHeaderViewRepository extends JpaRepository<FacVerifiFacingWebV, BigInteger> {
}