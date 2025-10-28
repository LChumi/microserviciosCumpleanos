package com.cumpleanos.ws.persistence.models;

import com.cumpleanos.ws.utils.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class User {

    @Id
    private String nickName;
    private String fullName;
    private Status status; // por ejemplo: ONLINE / OFFLINE
}
