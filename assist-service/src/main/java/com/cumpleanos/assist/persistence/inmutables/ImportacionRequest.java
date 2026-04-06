package com.cumpleanos.assist.persistence.inmutables;

import java.math.BigInteger;
import java.util.List;

public record ImportacionRequest(
        BigInteger ccoImportacion,
        Long empresa,
        List<BigInteger> ccoOrdenes
) {}