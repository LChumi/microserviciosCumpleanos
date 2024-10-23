package com.cumpleanos.notification.service.implementation;

import com.cumpleanos.notification.service.interfaces.IEmailService;
import com.cumpleanos.notification.utils.record.EmailRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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
}
