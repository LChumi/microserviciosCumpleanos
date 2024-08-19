package com.cumpleanos.mongo.persistence.models.mails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "destinatarios")
public class ContactAddress implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String direccion;
}
