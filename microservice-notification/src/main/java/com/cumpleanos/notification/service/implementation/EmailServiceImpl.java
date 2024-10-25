package com.cumpleanos.notification.service.implementation;

import com.cumpleanos.notification.service.interfaces.IEmailService;
import com.cumpleanos.notification.utils.record.EmailRecord;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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
}
