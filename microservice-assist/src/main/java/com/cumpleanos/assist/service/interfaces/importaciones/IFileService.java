package com.cumpleanos.assist.service.interfaces.importaciones;

import com.cumpleanos.assist.persistence.dto.OrdenComrpaListDTO;
import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {

    List<ProductImportTransformer> readExcelFile(MultipartFile file, Long empresa);

    OrdenComrpaListDTO getListSCi(MultipartFile file, Long empresa);
}
