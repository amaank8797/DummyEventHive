package com.amaan.eventhive.repository;

import com.amaan.eventhive.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {

    List<TicketType> findByEvent_EventId(Long eventId);
}