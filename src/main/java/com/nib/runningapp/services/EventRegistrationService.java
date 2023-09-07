package com.nib.runningapp.services;

import com.nib.runningapp.dtos.EventDTO;
import com.nib.runningapp.dtos.EventRegistrationDTO;

import java.util.List;

public interface EventRegistrationService {
    EventRegistrationDTO registerForEvent(Long userId, Long eventId);
    List<EventDTO> getAllEventsRegisteredByUser(Long userId);
    EventRegistrationDTO cancelRegistration(Long userId, Long eventId);
}
