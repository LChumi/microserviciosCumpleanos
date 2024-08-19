package com.cumpleanos.mongo.service.interfaces;

import com.cumpleanos.common.dtos.IndexNowRequest;
import com.cumpleanos.mongo.persistence.models.app.IndexNowConfig;

public interface IIndexNowConfigService extends IGenericService<IndexNowConfig, String> {

    IndexNowRequest getByAppName(String appName);

    IndexNowRequest addRoute(String appName, String route);

    IndexNowRequest removeRoute(String appName, String route);
}
