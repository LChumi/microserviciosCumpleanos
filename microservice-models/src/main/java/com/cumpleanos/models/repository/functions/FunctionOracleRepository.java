package com.cumpleanos.models.repository.functions;

import core.cumpleanos.models.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionOracleRepository extends JpaRepository<Cliente,Long> {
    @Query(value = "SELECT AST_FAC1.verificar_ruc_juridico(:ruc) FROM DUAL", nativeQuery = true)
    Long verificarRucJuridico(@Param("ruc") String ruc);

    @Query(value = "SELECT ast_gen.parametro(:empresa, :sigla, :secuencia, :almacen, :tipo) FROM DUAL", nativeQuery = true)
    Long ejecutarAstGen(@Param("empresa") int empresa,
                        @Param("sigla") String sigla,
                        @Param("secuencia") String secuencia,
                        @Param("almacen") String almacen,
                        @Param("tipo") int tipo);
}