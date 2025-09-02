package com.cumpleanos.common.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class IndexNowRequest {
    private String host;
    private String key;
    private String keyLocation;
    private List<String> urlList;
}
