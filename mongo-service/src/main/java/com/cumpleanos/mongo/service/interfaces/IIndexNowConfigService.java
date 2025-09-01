package com.cumpleanos.mongo.service.interfaces;

import com.cumpleanos.mongo.persistence.models.app.IndexNowConfig;

public interface IIndexNowConfigService extends IGenericService<IndexNowConfig, String> {

    IndexNowConfig getByAppName(String appName);

    IndexNowConfig addRoute(String appName, String route);

    IndexNowConfig removeRoute(String appName, String route);
}
