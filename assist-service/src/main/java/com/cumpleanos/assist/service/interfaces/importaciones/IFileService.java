package com.cumpleanos.assist.service.interfaces.importaciones;

import com.cumpleanos.assist.persistence.dto.OrdenCompraListDTO;
import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.List;

public interface IFileService {

    List<ProductImportTransformer> readExcelFile(MultipartFile file, Long empresa);

    OrdenCompraListDTO getListSCi(MultipartFile file, Long empresa, BigInteger cco);
}
