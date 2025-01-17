package com.cumpleanos.meta.persistence.models;

import java.util.List;

public record Value (Metadata metadata, List<Message> messages) {
}
