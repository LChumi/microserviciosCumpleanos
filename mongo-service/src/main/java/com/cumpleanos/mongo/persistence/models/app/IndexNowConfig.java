package com.cumpleanos.mongo.persistence.models.app;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "indexnow_configs")
public class IndexNowConfig {

    @Setter(AccessLevel.NONE)
    @Id
    private String Id;

    private String appName;
    private String domain;
    private String key;
    private String keyLocation;

    private List<String> urlList;
}