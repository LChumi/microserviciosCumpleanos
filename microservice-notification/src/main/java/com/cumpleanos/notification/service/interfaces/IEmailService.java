package com.cumpleanos.notification.service.interfaces;

import com.cumpleanos.notification.utils.record.EmailRecord;

public interface IEmailService {

    void sendMail(EmailRecord email);
}
