package com.amaan.eventhive.service;

import com.amaan.eventhive.entity.TicketType;
import com.amaan.eventhive.repository.TicketTypeRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TicketTypeService {

    private final TicketTypeRepository ticketTypeRepository;

    public TicketTypeService(TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
    }
    public List<TicketType> getTicketTypesByEventId(Long eventId) {
        return ticketTypeRepository.findByEvent_EventId(eventId);
    }



}
