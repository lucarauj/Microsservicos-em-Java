package com.lucas.events.services;

import com.lucas.events.domain.Event;
import com.lucas.events.domain.Subscription;
import com.lucas.events.dtos.EmailRequestDto;
import com.lucas.events.dtos.EventRequestDto;
import com.lucas.events.exceptions.EventFullException;
import com.lucas.events.exceptions.EventNotFoundException;
import com.lucas.events.repositories.EventRepository;
import com.lucas.events.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EmailServiceClient emailServiceClient;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getUpcomingEvents() {
        return eventRepository.findUpcomingEvents(LocalDateTime.now());
    }

    public Event createEvent(EventRequestDto eventRequest) {
        Event newEvent = new Event(eventRequest);
        return eventRepository.save(newEvent);
    }

    private Boolean isEventFull(Event event){
        return event.getRegisteredParticipants() >= event.getMaxParticipants();
    }

    public void registerParticipant(String eventId, String participantEmail) {
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);

        if(isEventFull(event)) {
            throw new EventFullException();
        }

        Subscription subscription = new Subscription(event, participantEmail);
        subscriptionRepository.save(subscription);

        event.setRegisteredParticipants(event.getRegisteredParticipants() + 1);

        EmailRequestDto emailRequest = new EmailRequestDto(participantEmail, "Confirmação de Inscrição", "Você foi inscrito no evento com sucesso!");

        emailServiceClient.sendEmail(emailRequest);
    }
}
