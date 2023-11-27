package com.email.service.model;

public interface EmailSenderUseCase {

    void sendEmail(String to, String subject, String text);
}
