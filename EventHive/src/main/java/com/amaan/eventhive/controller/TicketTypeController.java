package com.amaan.eventhive.controller;

import com.amaan.eventhive.entity.TicketType;
import com.amaan.eventhive.service.TicketTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class TicketTypeController {

    private final TicketTypeService ticketTypeService;

    public TicketTypeController(TicketTypeService ticketTypeService) {
        this.ticketTypeService = ticketTypeService;
    }

    @GetMapping("/{eventId}/ticket-types")
    public List<TicketType> getTicketTypesByEventId(@PathVariable Long eventId) {

        return ticketTypeService.getTicketTypesByEventId(eventId);
    }
}