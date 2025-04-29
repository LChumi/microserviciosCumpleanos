package com.cumpleanos.mongo.service.interfaces;

import com.cumpleanos.mongo.persistence.models.company.CompanyParameters;
import com.cumpleanos.mongo.utils.LogoType;
import org.springframework.web.multipart.MultipartFile;

public interface ICompanyParametersService extends IGenericService<CompanyParameters, String> {

    CompanyParameters getByCompanyId(Long companyId);

    CompanyParameters updateLogoBase64(String id, LogoType tipo, MultipartFile file);
}
