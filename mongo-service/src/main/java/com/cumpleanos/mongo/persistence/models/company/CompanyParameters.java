package com.cumpleanos.mongo.persistence.models.company;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "company_parameters")
@CompoundIndexes({
        @CompoundIndex(name = "company_parameters_idx", def = "{'companyId':1}", unique = true)
})
public class CompanyParameters  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private Long companyId;

    private String primaryColor;
    private String secondaryColor;
    private String lightColor;
    private String darkColor;
    private String textColor;

    private String primaryLogo;
    private String secondaryLogo;

    private String privacyPolicy;
}
