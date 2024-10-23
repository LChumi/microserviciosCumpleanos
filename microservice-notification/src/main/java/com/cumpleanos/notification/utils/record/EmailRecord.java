package com.cumpleanos.notification.utils.record;

public record EmailRecord(String[] toUser, String subject, String message) {
}
