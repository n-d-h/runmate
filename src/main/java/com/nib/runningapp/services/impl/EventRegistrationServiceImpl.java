package com.nib.runningapp.services.impl;

import com.nib.runningapp.dtos.EventDTO;
import com.nib.runningapp.dtos.EventRegistrationDTO;
import com.nib.runningapp.dtos.RunningSessionDTO;
import com.nib.runningapp.entities.Event;
import com.nib.runningapp.entities.EventRegistration;
import com.nib.runningapp.mappers.EventMapper;
import com.nib.runningapp.mappers.EventRegistrationMapper;
import com.nib.runningapp.repositories.EventRegistrationRepository;
import com.nib.runningapp.services.EventRegistrationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class EventRegistrationServiceImpl implements EventRegistrationService {

    private final EventRegistrationRepository eventRegistrationRepository;

    @Override
    public EventRegistrationDTO registerForEvent(Long userId, Long eventId) {
        EventRegistration eventRegistrationExists = eventRegistrationRepository.findByUserIdAndEventId(userId, eventId);
        if (eventRegistrationExists != null && eventRegistrationExists.getStatus()) {
            return null;
        }
        if(eventRegistrationExists != null && !eventRegistrationExists.getStatus()){
            eventRegistrationExists.setStatus(true);
            EventRegistration updatedEventRegistration = eventRegistrationRepository.save(eventRegistrationExists);
            return EventRegistrationMapper.INSTANCE.toDTO(updatedEventRegistration);
        }

        EventRegistrationDTO eventRegistrationDTO = new EventRegistrationDTO();
        eventRegistrationDTO.setUserId(userId);
        eventRegistrationDTO.setEventId(eventId);
        eventRegistrationDTO.setRegistrationDate(new java.util.Date());

        EventRegistration eventRegistration = EventRegistrationMapper.INSTANCE.toEntity(eventRegistrationDTO);
        eventRegistration.setStatus(true);

        EventRegistration savedEventRegistration = eventRegistrationRepository.save(eventRegistration);
        return EventRegistrationMapper.INSTANCE.toDTO(savedEventRegistration);
    }

    public List<EventDTO> getAllEventsRegisteredByUser(Long userId) {
        List<Event> events = eventRegistrationRepository.findAllActiveEventRegisteredByUser(userId);
        return events.stream().map(EventMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    @Override
    public EventRegistrationDTO cancelRegistration(Long userId, Long eventId) {
        EventRegistration eventRegistration = eventRegistrationRepository.findByUserIdAndEventId(userId, eventId);
        if (eventRegistration == null) {
            return null;
        }
        eventRegistration.setStatus(false);
        EventRegistration updatedEventRegistration = eventRegistrationRepository.save(eventRegistration);
        return EventRegistrationMapper.INSTANCE.toDTO(updatedEventRegistration);
    }
}
