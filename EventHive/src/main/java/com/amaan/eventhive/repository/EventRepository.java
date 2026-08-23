package com.amaan.eventhive.repository;

import com.amaan.eventhive.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}