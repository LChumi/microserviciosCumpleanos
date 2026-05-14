package com.cumpleanos.assist.persistence.inmutables;

import java.math.BigInteger;
import java.util.List;

public record ComprobantesCcoRequest(
        Long bodega,
        String usuario,
        Long empresa,
        List<BigInteger> ccoCodigos
) {
}