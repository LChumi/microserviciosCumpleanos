package com.cumpleanos.meta.persistence.models.whatsapp;

import java.util.List;

public record Value (Metadata metadata, List<Message> messages) {
}
