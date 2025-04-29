package com.cumpleanos.mongo.service.interfaces;

import com.cumpleanos.common.records.CompanyParametersRecord;
import com.cumpleanos.mongo.persistence.models.company.CompanyParameters;
import org.springframework.web.multipart.MultipartFile;

public interface ICompanyParametersService extends IGenericService<CompanyParameters, String> {

    CompanyParameters getByCompanyId(Long companyId);

    CompanyParametersRecord updateLogoBase64(Long id, Long tipo, MultipartFile file);
}
