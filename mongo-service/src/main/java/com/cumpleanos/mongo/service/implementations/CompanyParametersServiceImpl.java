package com.cumpleanos.mongo.service.implementations;

import com.cumpleanos.common.records.CompanyParametersRecord;
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

import static com.cumpleanos.mongo.utils.ImageUtils.resizeImagePreservingAspectRatio;

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

    /**
     * Metodo para convertir la imagen a base64 ya agregar en mongo
     * @param id id para buscar el objeto en mongo
     * @param tipo cuando se sube el logo primario o secundario
     * @param file archivo a convertir a base64
     * @return imagen en base64 ya agregada en mongo
     */
    @Override
    public CompanyParametersRecord updateLogoBase64(Long id, Long tipo, MultipartFile file) {
        CompanyParameters found = repository.findByCompanyId(id)
                .orElseThrow(() -> new DocumentNotFoundException("No se encontró datos de la empresa"));

        try {
            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("Archivo vacío");
            }

            if (!Objects.requireNonNull(file.getContentType()).toLowerCase().startsWith("image/")) {
                throw new IllegalArgumentException("El archivo no es una imagen");
            }

            long maxSize = 500 * 1024; // 500 KB
            if (file.getSize() > maxSize) {
                throw new IllegalArgumentException("El archivo excede el tamaño permitido (500 KB)");
            }

            BufferedImage image = ImageIO.read(file.getInputStream());
            BufferedImage resizedImage = resizeImagePreservingAspectRatio(image, 120, 120);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "png", baos); // SIEMPRE se guarda como PNG
            byte[] imageBytes = baos.toByteArray();

            String base64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes);

            LogoType tipoEnum = LogoType.fromValue(tipo);
            switch (tipoEnum) {
                case PRIMARY -> found.setPrimaryLogo(base64);
                case SECONDARY -> found.setSecondaryLogo(base64);
            }

            CompanyParameters comp =  repository.save(found);
            return new CompanyParametersRecord(
                    comp.getCompanyId(),
                    comp.getPrimaryColor(),
                    comp.getSecondaryColor(),
                    comp.getLightColor(),
                    comp.getDarkColor(),
                    comp.getPrimaryTextColor(),
                    comp.getSecondaryTextColor(),
                    comp.getTitleStyle(),
                    comp.getSubtitleStyle(),
                    comp.getPrimaryLogo(),
                    comp.getSecondaryLogo(),
                    comp.getPrivacyPolicy()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir el archivo a Base64", e);
        }
    }

}
