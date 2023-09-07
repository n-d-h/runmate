package com.nib.runningapp.services.impl;

import com.nib.runningapp.dtos.EventDTO;
import com.nib.runningapp.entities.Event;
import com.nib.runningapp.mappers.EventMapper;
import com.nib.runningapp.repositories.EventRepository;
import com.nib.runningapp.services.EventService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    
    @Override
    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = EventMapper.INSTANCE.toEntity(eventDTO);
        event.setStatus(true);
        event.setId(0L);
        Event savedEvent = eventRepository.save(event);
        return EventMapper.INSTANCE.toDTO(savedEvent);
    }

    @Override
    public EventDTO deleteEvent(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) {
            return null;
        }
        eventRepository.delete(event);
        return EventMapper.INSTANCE.toDTO(event);
    }

    @Override
    public EventDTO updateEvent(EventDTO eventDTO, Long id) {
        Event oldEvent = eventRepository.findById(id).orElse(null);
        if (oldEvent == null) {
            return null;
        }
        Event event = EventMapper.INSTANCE.toEntity(eventDTO);
        event.setId(id);
        event.setStatus(oldEvent.getStatus());
        Event updatedEvent = eventRepository.save(event);
        return EventMapper.INSTANCE.toDTO(updatedEvent);
    }

    @Override
    public EventDTO getEventById(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) {
            return null;
        }
        return EventMapper.INSTANCE.toDTO(event);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAllActiveEvent();
        return events.stream().map(EventMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }
}
