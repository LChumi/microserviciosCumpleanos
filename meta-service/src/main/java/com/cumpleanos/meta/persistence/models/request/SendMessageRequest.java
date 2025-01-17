package com.cumpleanos.meta.persistence.models.request;

import com.cumpleanos.meta.persistence.models.Text;

public record SendMessageRequest(String messaging_product, String to, Text text, Context context) {}