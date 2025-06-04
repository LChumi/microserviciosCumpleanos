package com.cumpleanos.notification.service.implementation;

import com.cumpleanos.common.records.EmailRecord;
import com.cumpleanos.notification.service.interfaces.IEmailService;
import jakarta.activation.DataHandler;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender mailSender;

    @Value("${email.sender}")
    private String emailUser;

    public void sendEmailWithHtmlAndAttachments(EmailRecord email, Map<String,byte[]> attachments) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMultipart rootMultipart = new MimeMultipart("mixed");

            //Html con Imagenes enbebidasd
            MimeBodyPart htmlAndImagesPart = buildHtmlInlineImages(email);
            rootMultipart.addBodyPart(htmlAndImagesPart);

            //Adjuntos si existen
            if (attachments != null && !attachments.isEmpty()) {
                for (Map.Entry<String,byte[]> entry : attachments.entrySet()) {
                    rootMultipart.addBodyPart(buildAttachmentPart(entry.getKey(), entry.getValue()));
                }
            }

            mimeMessage.setContent(rootMultipart);
            mimeMessage.setSubject(email.subject());
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", email.toUser())));
            mimeMessage.setFrom(new InternetAddress(emailUser));

            mailSender.send(mimeMessage);
        } catch (MessagingException | IOException e){
            log.error("Error al enviar el correo ", e);
            throw new MailSendException("Error al enviar el correo ", e);
        }
    }

    private MimeBodyPart buildHtmlInlineImages(EmailRecord email) throws MessagingException, IOException {
        MimeBodyPart relatedPart = new MimeBodyPart();
        MimeMultipart relatedMultipart = new MimeMultipart("related");

        String htmlContent = getHtmlTemplate(email.subject(), email.message());

        // 1. Insertar el HTML PRIMERO
        MimeBodyPart htmlBody = new MimeBodyPart();
        htmlBody.setContent(htmlContent, "text/html; charset=utf-8");
        relatedMultipart.addBodyPart(htmlBody); // <-- ¡Esto va primero!

        // 2. Insertar logo fijo con cid:logoCid
        Resource favicon = new ClassPathResource("images/logo.png");
        if (favicon.exists()) {
            MimeBodyPart logoPart = new MimeBodyPart();
            logoPart.setDataHandler(new DataHandler(
                    new ByteArrayDataSource(favicon.getInputStream(), "image/png")
            ));
            logoPart.setHeader("Content-ID", "<logoCid>");
            logoPart.setDisposition(MimeBodyPart.INLINE);
            relatedMultipart.addBodyPart(logoPart);
        } else {
            log.warn("Favicon no encontrado en classpath: images/logo.png");
        }

        // 3. Reemplazar y adjuntar imágenes externas
        Pattern pattern = Pattern.compile("<img[^>]+src=[\"'](https://[^\"']+)[\"'][^>]*>");
        Matcher matcher = pattern.matcher(htmlContent);
        int cidCounter = 0;

        while (matcher.find()) {
            String imageUrl = matcher.group(1);
            String cid = "cidImage" + (++cidCounter);

            try (InputStream in = new URL(imageUrl).openStream()) {
                byte[] imagesBytes = in.readAllBytes();

                MimeBodyPart imagePart = new MimeBodyPart();
                imagePart.setDataHandler(new DataHandler(new ByteArrayDataSource(imagesBytes, guessMimeType(imageUrl))));
                imagePart.setHeader("Content-ID", "<" + cid + ">");
                imagePart.setDisposition(MimeBodyPart.INLINE);

                relatedMultipart.addBodyPart(imagePart);

                // Reemplazo en HTML
                htmlContent = htmlContent.replace(imageUrl, "cid:" + cid);
            } catch (IOException e) {
                log.error("No se pudo descargar imagen externa: {}", imageUrl, e);
            }
        }

        // Finalmente, actualizar el contenido del HTML si fue modificado (opcional)
        // Si reemplazaste imágenes, podrías reescribir htmlBody.setContent(...) aquí
        // pero en general basta con colocarlo antes.

        relatedPart.setContent(relatedMultipart);
        return relatedPart;
    }

    private String getHtmlTemplate(String titulo, String contenido){
        try {
            Resource resource = new ClassPathResource("templates/email-template.html");
            String template = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            return template.replace("${titulo}", titulo).replace("${contenido}", contenido);
        } catch (IOException e) {
            log.error("No se pudo cargar la plantilla de correo", e);
            throw new UncheckedIOException("No se pudo cargar la plantilla de correo", e);
        }
    }

    private MimeBodyPart buildAttachmentPart(String fileName, byte[] fileData) throws  MessagingException {
        MimeBodyPart attachmentPart = new MimeBodyPart();
        String mimeType = guessMimeType(fileName);
        attachmentPart.setDataHandler(new DataHandler(new ByteArrayDataSource(fileData, mimeType)));
        attachmentPart.setFileName(fileName);
        return attachmentPart;
    }

    private String guessMimeType(String filename) {
        if (filename.endsWith(".pdf")) return "application/pdf";
        if (filename.endsWith(".xml")) return "application/xml";
        if (filename.endsWith(".xlsx")) return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        if (filename.endsWith(".xls")) return "application/vnd.ms-excel";
        if (filename.endsWith(".png")) return "image/png";
        if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) return "image/jpeg";
        return "application/octet-stream";
    }

}