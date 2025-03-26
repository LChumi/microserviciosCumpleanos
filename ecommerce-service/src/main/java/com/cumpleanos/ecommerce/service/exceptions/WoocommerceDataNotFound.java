package com.cumpleanos.ecommerce.service.exceptions;

public class WoocommerceDataNotFound extends RuntimeException {

    public WoocommerceDataNotFound(String message) {
        super(message);
    }

    public WoocommerceDataNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
