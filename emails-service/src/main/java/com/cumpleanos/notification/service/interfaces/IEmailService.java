package com.cumpleanos.notification.service.interfaces;

import com.cumpleanos.common.records.EmailRecord;

import java.util.Map;

public interface IEmailService {

    void sendEmailWithHtmlAndAttachments(EmailRecord email, Map<String, byte[]> attachments);

}
