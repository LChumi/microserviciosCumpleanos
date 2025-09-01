package com.cumpleanos.mongo.service.implementations;

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
    public IndexNowConfig getByAppName(String appName) {
        return repository.getByAppName(appName)
                .orElseThrow(() -> new DocumentNotFoundException("App Not Found"));
    }

    @Override
    public IndexNowConfig addRoute(String appName, String route) {
        IndexNowConfig app = repository.getByAppName(appName).orElseThrow(() -> new DocumentNotFoundException("App Not Found"));

        Set<String> existingRoutes = new HashSet<>(app.getUrlList());

        if (existingRoutes.contains(route)) {
            throw new IllegalArgumentException("Route already exists:" + route);
        }

        app.getUrlList().add(route);
        return repository.save(app);
    }

    @Override
    public IndexNowConfig removeRoute(String appName, String route) {
        IndexNowConfig app = repository.getByAppName(appName).orElseThrow(() -> new DocumentNotFoundException("App Not Found"));

        boolean removed = app.getUrlList().removeIf(url -> url.equals(route));

        if (!removed) {
            throw new IllegalArgumentException("Route Not Found:" + route);
        }

        return repository.save(app);
    }
}
