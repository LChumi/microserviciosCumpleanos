package com.cumpleanos.notification.service.interfaces;

import com.cumpleanos.common.records.EmailRecord;

import java.util.Map;

public interface IEmailService {

    void sendMailAttach(EmailRecord email, String nameAttach, byte[] fileAttach);

    void sendEmailWithHtmlAndAttachments(EmailRecord email, Map<String, byte[]> attachments);
}
