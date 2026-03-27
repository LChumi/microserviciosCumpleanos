package com.cumpleanos.assist.persistence.inmutables;

import java.math.BigInteger;
import java.util.List;

public record ImportacionRequest(
        BigInteger ccoImportacion,
        List<BigInteger> ccoOrdenes
) {}