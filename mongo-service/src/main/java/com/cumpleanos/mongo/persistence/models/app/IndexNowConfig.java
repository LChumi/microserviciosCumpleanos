package com.cumpleanos.mongo.persistence.models.app;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "indexnow_configs")
public class IndexNowConfig {

    @Id
    private String Id;

    private String appName;
    private String domain;
    private String key;
    private String keyLocation;

    private List<String> urlList;

    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}