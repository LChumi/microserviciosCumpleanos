package com.cumpleanos.ecommerce.service.implementations;

import com.cumpleanos.ecommerce.configuration.WooCommerceProperties;
import com.cumpleanos.ecommerce.service.exceptions.ResourceImageNotFound;
import com.cumpleanos.ecommerce.service.exceptions.WoocommerceDataNotFound;
import com.cumpleanos.ecommerce.service.http.ImagesClient;
import com.cumpleanos.ecommerce.service.http.WooCommerceMediaClient;
import com.cumpleanos.ecommerce.utils.CustomMultipartFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class WooCommerceMediaServiceImpl {

    private final WooCommerceMediaClient wooCommerceMediaClient;
    private final ImagesClient imagesClient;
    private final WooCommerceProperties properties;

    public Integer uploadImage(String id) throws IOException {

        ResponseEntity<Resource> response = imagesClient.getImage(id);

        if (response.getBody() == null) {
            throw new ResourceImageNotFound("Imagen no encontrada");
        }

        Resource imageResource = response.getBody();

        if (response.getStatusCode().is2xxSuccessful() && imageResource.exists()) {
            MultipartFile imageFile = convertResourceToMultipartFile(imageResource, id);
            String token = "Bearer "+properties.getToken();
            Map<String, Object> responseWoocommerce = wooCommerceMediaClient.uploadImage(
                    token,
                    imageFile
            );
            if (responseWoocommerce.isEmpty()) {
                throw new WoocommerceDataNotFound("No se puedo subir la imagen en el servicio");
            }
            return (Integer) responseWoocommerce.get("id");
        }
        return null;
    }

    private MultipartFile convertResourceToMultipartFile(Resource resource, String id) throws IOException {
        byte[] imageBytes = StreamUtils.copyToByteArray(resource.getInputStream());
        String fileName = id + ".jpg";
        return new CustomMultipartFile(
                imageBytes,
                fileName,
                "image/jpeg"
        );
    }

}
