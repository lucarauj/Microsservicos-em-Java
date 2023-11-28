package com.lucas.events.controllers;

import com.lucas.events.domain.Event;
import com.lucas.events.dtos.EventRequestDto;
import com.lucas.events.dtos.SubscriptionRequestDto;
import com.lucas.events.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/upcoming")
    public List<Event> getUpcomingEvents() {
        return eventService.getUpcomingEvents();
    }

    @PostMapping
    public Event createEvent(@RequestBody EventRequestDto event) {
        return eventService.createEvent(event);
    }

    @PostMapping("/{eventId}/register")
    public void registerParticipant(@PathVariable String eventId, @RequestBody SubscriptionRequestDto subscriptionRequest) {
        eventService.registerParticipant(eventId, subscriptionRequest.participantEmail());
    }
}
