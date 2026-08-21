package com.cumpleanos.common.records;

import java.util.List;

public record EmpresaCodigosRequest(
        Long empresa,
        List<Long> codigos
) {
}
