package com.cumpleanos.assist.persistence.repository.views;

import com.cumpleanos.core.models.views.FacRevprodWebV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface PendingInvoiceProductDetailView extends JpaRepository<FacRevprodWebV, String> {

    List<FacRevprodWebV> findByCcoCodigo(BigInteger ccoCodigo);
}
