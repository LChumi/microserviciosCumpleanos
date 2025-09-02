package com.cumpleanos.mongo.service.implementations;

import com.cumpleanos.common.dtos.IndexNowRequest;
import com.cumpleanos.mongo.persistence.models.app.IndexNowConfig;
import com.cumpleanos.mongo.persistence.repository.IndexNowConfigRepository;
import com.cumpleanos.mongo.service.exceptions.DocumentNotFoundException;
import com.cumpleanos.mongo.service.interfaces.IIndexNowConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IndexNowCongServiceImpl extends GenericServiceImpl<IndexNowConfig, String>  implements IIndexNowConfigService {

    private final IndexNowConfigRepository repository;

    @Override
    public CrudRepository<IndexNowConfig, String> getRepository() {
        return repository;
    }

    @Override
    public IndexNowRequest getByAppName(String appName) {
        IndexNowConfig response = repository.getByAppName(appName)
                .orElseThrow(() -> new DocumentNotFoundException("App Not Found"));

        return buildResponse(response);
    }

    @Override
    public IndexNowRequest addRoute(String appName, String route) {
        IndexNowConfig app = repository.getByAppName(appName).orElseThrow(() -> new DocumentNotFoundException("App Not Found"));

        Set<String> existingRoutes = new HashSet<>(app.getUrlList());

        if (existingRoutes.contains(route)) {
            throw new IllegalArgumentException("Route already exists:" + route);
        }

        app.getUrlList().add(route);
        IndexNowConfig response = repository.save(app);
        return buildResponse(response);
    }

    @Override
    public IndexNowRequest removeRoute(String appName, String route) {
        IndexNowConfig app = repository.getByAppName(appName).orElseThrow(() -> new DocumentNotFoundException("App Not Found"));

        boolean removed = app.getUrlList().removeIf(url -> url.equals(route));

        if (!removed) {
            throw new IllegalArgumentException("Route Not Found:" + route);
        }

        IndexNowConfig response = repository.save(app);

        return buildResponse(response);
    }

    private IndexNowRequest buildResponse(IndexNowConfig config) {
        return IndexNowRequest.builder()
                .host(config.getHost())
                .key(config.getKey())
                .keyLocation(config.getKeyLocation())
                .urlList(config.getUrlList())
                .build();
    }
}
