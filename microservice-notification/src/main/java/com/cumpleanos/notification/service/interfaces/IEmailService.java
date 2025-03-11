package com.cumpleanos.notification.service.interfaces;

import com.cumpleanos.notification.utils.record.EmailRecord;

public interface IEmailService {

    void sendMailHtml(EmailRecord email);
    void sendMail(EmailRecord email);
    void sendMailAttach(EmailRecord email, String nameAttach, byte[] fileAttach);
}
