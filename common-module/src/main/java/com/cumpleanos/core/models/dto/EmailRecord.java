package com.cumpleanos.core.models.dto;

public record EmailRecord(String[] toUser, String subject, String message) {
}
