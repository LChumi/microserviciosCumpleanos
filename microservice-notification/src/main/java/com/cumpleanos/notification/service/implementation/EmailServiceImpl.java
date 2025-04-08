package com.cumpleanos.notification.service.implementation;

import com.cumpleanos.common.records.EmailRecord;
import com.cumpleanos.notification.service.interfaces.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender mailSender;

    @Value("${email.sender}")
    private String emailUser;

    @Override
    public void sendMailHtml(EmailRecord email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(emailUser);
            helper.setTo(email.toUser());
            helper.setSubject(email.subject());
            helper.setText(email.message(), true); // true indica que el contenido es HTML

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

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
    public void sendMailAttach(EmailRecord email, String nameAttach, byte[] fileAttach) {
        if (nameAttach == null || nameAttach.isBlank()) {
            throw new IllegalArgumentException("El nombre del adjunto no puede ser nulo o vacío");
        }
        if (fileAttach == null || fileAttach.length == 0) {
            throw new IllegalArgumentException("El archivo adjunto no puede ser nulo o vacío");
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(emailUser);
            helper.setTo(email.toUser());
            helper.setSubject(email.subject());
            helper.setText(email.message());

            ByteArrayResource attach = new ByteArrayResource(fileAttach);
            helper.addAttachment(nameAttach, attach);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MailSendException("Error al enviar el correo con adjunto", e);
        }
    }
}
