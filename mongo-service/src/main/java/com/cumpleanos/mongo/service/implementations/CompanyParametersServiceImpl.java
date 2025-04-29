package com.cumpleanos.mongo.service.implementations;

import com.cumpleanos.mongo.persistence.models.company.CompanyParameters;
import com.cumpleanos.mongo.persistence.repository.CompanyParametersRepository;
import com.cumpleanos.mongo.service.exceptions.DocumentNotFoundException;
import com.cumpleanos.mongo.service.interfaces.ICompanyParametersService;
import com.cumpleanos.mongo.utils.LogoType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyParametersServiceImpl extends GenericServiceImpl<CompanyParameters, String> implements ICompanyParametersService {

    private final CompanyParametersRepository repository;

    @Override
    public CrudRepository<CompanyParameters, String> getRepository() {
        return repository;
    }

    @Override
    public CompanyParameters getByCompanyId(Long companyId) {
        return repository.findByCompanyId(companyId).orElseThrow(() -> new DocumentNotFoundException("No se encontro datos de la empresa con id: " + companyId));
    }

    @Override
    public CompanyParameters updateLogoBase64(String id, LogoType tipo, MultipartFile file) {
        CompanyParameters found = repository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException("No se encontro datos de la empresa"));

        try {

            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("Archivo vació");
            }

            if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")) {
                throw new IllegalArgumentException("El archivo no es una imagen");
            }

            String base64 = "data:" + file.getContentType() + ";base64," +
                    Base64.getEncoder().encodeToString(file.getBytes());

            switch (tipo){
                case PRIMARY -> found.setPrimaryLogo(base64);
                case SECONDARY -> found.setSecondaryLogo(base64);
            }

            return repository.save(found);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir el archivo a Base64", e);
        }
    }
}
