package com.nib.runningapp.services;

import com.nib.runningapp.dtos.EventDTO;

import java.util.List;

public interface EventService {
    EventDTO createEvent(EventDTO eventDTO);
    EventDTO deleteEvent(Long id);
    EventDTO updateEvent(EventDTO eventDTO, Long id);
    EventDTO getEventById(Long id);
    List<EventDTO> getAllEvents();
}
