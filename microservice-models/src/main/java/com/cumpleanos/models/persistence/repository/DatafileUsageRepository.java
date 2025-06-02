package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.common.dtos.DatafileUsage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor_ = { @Autowired})
public class DatafileUsageRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<DatafileUsage> getDatafileUsages() {
        String sql = """
                SELECT DF.TABLESPACE_NAME,
                                   DF.FILE_NAME,
                                   ROUND(DF.BYTES / 1024 / 1024 / 1024, 2) AS SIZE_GB,
                                   ROUND(FREE.FREE_KB / 1024 / 1024, 2) AS FREE_GB,
                                   ROUND((FREE.FREE_KB / 1024) / (DF.BYTES / 1024 / 1024) * 100, 2) AS FREE_PERCENT
                              FROM DBA_DATA_FILES DF
                              JOIN (
                                   SELECT FILE_ID, SUM(BYTES) / 1024 AS FREE_KB
                                     FROM DBA_FREE_SPACE
                                    GROUP BY FILE_ID
                                   ) FREE
                                ON DF.FILE_ID = FREE.FILE_ID
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new DatafileUsage(
                rs.getString("TABLESPACE_NAME"),
                rs.getString("FILE_NAME"),
                rs.getDouble("SIZE_GB"),
                rs.getDouble("FREE_GB"),
                rs.getDouble("FREE_PERCENT")
        ));
    }

}
