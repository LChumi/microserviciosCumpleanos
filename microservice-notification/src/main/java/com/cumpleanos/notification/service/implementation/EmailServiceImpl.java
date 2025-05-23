package com.cumpleanos.notification.service.implementation;

import com.cumpleanos.common.records.EmailRecord;
import com.cumpleanos.notification.service.interfaces.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender mailSender;

    @Value("${email.sender}")
    private String emailUser;

    @Override
    public void sendMail(EmailRecord email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(emailUser);
        mailMessage.setTo(email.toUser());
        mailMessage.setSubject(email.subject());

        mailMessage.setText(email.message());

        mailSender.send(mailMessage);
    }

    @Override
    public void sendMailHtml(EmailRecord email) {
        sendHtmlEmail(email, null, null);
    }

    @Override
    public void sendMailAttach(EmailRecord email, String nameAttach, byte[] fileAttach) {
        if (nameAttach == null || nameAttach.isBlank()) {
            throw new IllegalArgumentException("El nombre del adjunto no puede ser nulo o vacío");
        }
        if (fileAttach == null || fileAttach.length == 0) {
            throw new IllegalArgumentException("El archivo adjunto no puede ser nulo o vacío");
        }

        sendHtmlEmail(email, nameAttach, fileAttach);
    }

    private void sendHtmlEmail(EmailRecord email, String nameAttach, byte[] fileAttach) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            // true = multipart
            MimeMessageHelper helper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_RELATED,
                    StandardCharsets.UTF_8.name()
            );

            helper.setFrom(emailUser);
            helper.setTo(email.toUser());
            helper.setSubject(email.subject());

            // Carga del logo
            Resource logo = new ClassPathResource("images/logo.png");
            if (!logo.exists()) {
                throw new RuntimeException("La imagen logo.png no fue encontrada");
            }

            helper.setText(getHtmlTemplate(email.subject(), email.message()), true);
            helper.addInline("logoCid", logo, "image/png");

            // Adjuntos normales
            if (nameAttach != null && fileAttach != null){
                helper.addAttachment(nameAttach, new ByteArrayResource(fileAttach));
            }

            mailSender.send(mimeMessage);

        } catch (MessagingException e){
            throw new MailSendException("Error al enviar el correo ", e);
        }
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
}
