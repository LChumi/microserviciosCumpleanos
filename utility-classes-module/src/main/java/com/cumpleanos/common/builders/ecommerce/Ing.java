package com.cumpleanos.common.builders.ecommerce;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ing {
    private String first_name;
    private String last_name;
    private String company;
    private String address_1;
    private String address_2;
    private String city;
    private String state;
    private String postcode;
    private String country;
    private String email;
    private String phone;
}
