package com.cumpleanos.meta.persistence.models.whatsapp.request;

import com.cumpleanos.meta.persistence.models.whatsapp.Text;

public record SendMessageRequest(String messaging_product, String to, Text text, Context context) {}