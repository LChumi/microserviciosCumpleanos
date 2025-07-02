package com.cumpleanos.ecommerce.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "woocommerce")
public class WooCommerceProperties {
    private String url;
    private String client;
    private String secretClient;
    private String username;
    private String password;
}
