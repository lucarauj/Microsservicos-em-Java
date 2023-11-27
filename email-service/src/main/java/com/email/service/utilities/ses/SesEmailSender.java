package com.email.service.utilities.ses;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.email.service.model.exceptions.EmailServiceException;
import com.email.service.view.EmailSenderGateway;
import org.springframework.stereotype.Service;

@Service
public class SesEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    public SesEmailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        SendEmailRequest request = new SendEmailRequest()
                .withSource("lukas.martins193@gmail.com")
                .withDestination(new Destination().withToAddresses(to))
                .withMessage(new Message()
                        .withSubject(new Content(subject))
                        .withBody(new Body().withText(new Content(text)))
                );
        try {
            this.amazonSimpleEmailService.sendEmail(request);
        } catch (AmazonServiceException exception) {
            throw new EmailServiceException("Falha ao enviar e-mail!", exception);
        }
    }
}
