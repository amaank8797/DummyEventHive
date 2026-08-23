package com.amaan.eventhive.dto;

import java.time.LocalDateTime;

public class EventResponseDTO {

    private Long eventId;
    private String title;
    private String description;
    private String venue;
    private LocalDateTime eventDate;
    private String category;

    public EventResponseDTO() {
    }

    public EventResponseDTO(Long eventId, String title, String description,
                            String venue, LocalDateTime eventDate,
                            String category) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.venue = venue;
        this.eventDate = eventDate;
        this.category = category;
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
}