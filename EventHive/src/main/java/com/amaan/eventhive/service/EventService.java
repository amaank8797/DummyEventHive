package com.amaan.eventhive.service;

import com.amaan.eventhive.repository.EventRepository;
import com.amaan.eventhive.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.amaan.eventhive.entity.Event;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Event not found with id: " + eventId
                        ));
    }


}
