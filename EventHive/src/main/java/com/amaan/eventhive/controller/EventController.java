package com.amaan.eventhive.controller;

import com.amaan.eventhive.entity.Event;
import com.amaan.eventhive.service.EventService;
import com.amaan.eventhive.dto.EventResponseDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/api/v1/events")
    public List<EventResponseDTO> getAllEvents() {

        return eventService.getAllEvents()
                .stream()
                .map(event -> new EventResponseDTO(
                        event.getEventId(),
                        event.getTitle(),
                        event.getDescription(),
                        event.getVenue(),
                        event.getEventDate(),
                        event.getCategory()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v1/events/{eventId}")
    public EventResponseDTO getEventById(@PathVariable Long eventId) {

        Event event = eventService.getEventById(eventId);

        if (event == null) {
            return null;
        }

        return new EventResponseDTO(
                event.getEventId(),
                event.getTitle(),
                event.getDescription(),
                event.getVenue(),
                event.getEventDate(),
                event.getCategory()
        );
    }
}