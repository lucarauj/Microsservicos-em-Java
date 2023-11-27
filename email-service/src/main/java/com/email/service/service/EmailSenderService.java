package com.email.service.service;

import com.email.service.model.EmailSenderUseCase;
import com.email.service.view.EmailSenderGateway;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements EmailSenderUseCase {

    private final EmailSenderGateway emailSenderGateway;

    public EmailSenderService(EmailSenderGateway emailSenderGateway) {
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        this.emailSenderGateway.sendEmail(to, subject, text);
    }
}
