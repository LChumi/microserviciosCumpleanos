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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Objects;

import static com.cumpleanos.mongo.utils.ImageUtils.resizeImage;

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
    public CompanyParameters updateLogoBase64(Long id, Long tipo, MultipartFile file) {
        CompanyParameters found = repository.findByCompanyId(id)
                .orElseThrow(() -> new DocumentNotFoundException("No se encontró datos de la empresa"));

        try {
            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("Archivo vacío");
            }

            if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")) {
                throw new IllegalArgumentException("El archivo no es una imagen");
            }

            BufferedImage image = ImageIO.read(file.getInputStream());
            BufferedImage resizedImage = resizeImage(image, 200, 200);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            String format = file.getContentType().contains("png") ? "png" : "jpg";
            ImageIO.write(resizedImage, format, baos); // Primero escribe
            byte[] imageBytes = baos.toByteArray();    // Luego obtén los bytes

            String base64 = "data:" + file.getContentType() + ";base64," +
                    Base64.getEncoder().encodeToString(imageBytes);

            LogoType tipoEnum = LogoType.fromValue(tipo);

            switch (tipoEnum) {
                case PRIMARY -> found.setPrimaryLogo(base64);
                case SECONDARY -> found.setSecondaryLogo(base64);
            }

            return repository.save(found);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir el archivo a Base64", e);
        }
    }

}
