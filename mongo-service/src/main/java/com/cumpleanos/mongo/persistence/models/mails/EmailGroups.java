package com.cumpleanos.mongo.persistence.models.mails;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "emailGroups")
@CompoundIndexes({
        @CompoundIndex(name = "emailGroups_idx", def = "{'tipo':1}", unique = true)
})
public class EmailGroups implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    private Long tipo;
    private String descripcion;
    private List<ContactAddress> destinatarios;
}
