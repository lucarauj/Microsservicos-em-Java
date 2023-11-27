package com.email.service.view;

public interface EmailSenderGateway {

    void sendEmail(String to, String subject, String text);
}
