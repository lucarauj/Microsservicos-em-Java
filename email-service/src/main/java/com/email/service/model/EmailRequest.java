package com.email.service.model;

public record EmailRequest(String to, String subject, String text) {
}
