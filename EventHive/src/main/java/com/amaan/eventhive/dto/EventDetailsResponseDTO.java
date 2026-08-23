package com.amaan.eventhive.dto;

import java.time.LocalDateTime;
import java.util.List;

public class EventDetailsResponseDTO {

    private Long eventId;
    private String title;
    private String description;
    private String venue;
    private LocalDateTime eventDate;
    private String category;
    private List<TicketTypeResponseDTO> ticketTypes;

    public EventDetailsResponseDTO() {
    }

    public EventDetailsResponseDTO(Long eventId, String title,
                                   String description, String venue,
                                   LocalDateTime eventDate, String category,
                                   List<TicketTypeResponseDTO> ticketTypes) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.venue = venue;
        this.eventDate = eventDate;
        this.category = category;
        this.ticketTypes = ticketTypes;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<TicketTypeResponseDTO> getTicketTypes() {
        return ticketTypes;
    }

    public void setTicketTypes(List<TicketTypeResponseDTO> ticketTypes) {
        this.ticketTypes = ticketTypes;
    }
}