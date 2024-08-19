package com.cumpleanos.mongo.service.interfaces;

import com.cumpleanos.mongo.persistence.models.mails.ContactAddress;
import com.cumpleanos.mongo.persistence.models.mails.EmailGroups;

public interface IEmailGroupsService extends IGenericService<EmailGroups, String> {

    EmailGroups getByTipo(Long tipo);

    EmailGroups addAddressee(Long tipo, ContactAddress addressee);

    EmailGroups removeAddressee(Long tipo, String addressee);
}
