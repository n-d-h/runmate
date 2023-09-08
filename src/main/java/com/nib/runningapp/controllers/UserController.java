package com.nib.runningapp.controllers;

import com.nib.runningapp.dtos.EventDTO;
import com.nib.runningapp.dtos.EventRegistrationDTO;
import com.nib.runningapp.dtos.RunningSessionDTO;
import com.nib.runningapp.services.EventRegistrationService;
import com.nib.runningapp.services.RunningSessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "User API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final RunningSessionService runningSessionService;
    private final EventRegistrationService eventRegistrationService;

    // Running Session API For User
    @GetMapping("/{userId}/running-sessions")
    public ResponseEntity<?> getRunningSessionsByUserId(@PathVariable("userId") Long userId) {
        List<RunningSessionDTO> runningSessions = runningSessionService.getAllRunningSessionsByUserId(userId);
        return ResponseEntity.ok(runningSessions);
    }

    @PostMapping("/{userId}/running-sessions")
    public ResponseEntity<?> createRunningSession(
            @RequestBody RunningSessionDTO runningSessionDTO, @PathVariable("userId") Long userId) {
        runningSessionDTO.setUserId(userId);
        RunningSessionDTO createdRunningSession = runningSessionService.createRunningSession(runningSessionDTO);
        return ResponseEntity.created(null).body(createdRunningSession);
    }

    @PutMapping("/{userId}/running-sessions/{runningSessionId}")
    public ResponseEntity<?> updateRunningSession(
            @RequestBody RunningSessionDTO runningSessionDTO, @PathVariable("userId") Long userId,
            @PathVariable("runningSessionId") Long runningSessionId) {
        runningSessionDTO.setUserId(userId);
        RunningSessionDTO updatedRunningSession = runningSessionService.updateRunningSession(runningSessionDTO, runningSessionId);
        return ResponseEntity.ok(updatedRunningSession);
    }

    @DeleteMapping("/{userId}/running-sessions/{runningSessionId}")
    public ResponseEntity<?> deleteRunningSession(
            @PathVariable("userId") Long userId, @PathVariable("runningSessionId") Long runningSessionId) {
        RunningSessionDTO deletedRunningSession = runningSessionService.deleteRunningSession(runningSessionId);
        return ResponseEntity.ok(deletedRunningSession);
    }

    // Event Registration API For User
    @PostMapping("/{userId}/event-registrations/{eventId}")
    public ResponseEntity<?> registerForEvent(
            @PathVariable("userId") Long userId, @PathVariable("eventId") Long eventId) {
        EventRegistrationDTO eventRegistrationDTO = eventRegistrationService.registerForEvent(userId, eventId);
        if (eventRegistrationDTO == null) {
            return ResponseEntity.badRequest().body(new HashMap<>(
                    Map.of("message", "User already registered for this event")
            ));
        }
        return ResponseEntity.ok(eventRegistrationDTO);
    }

    @GetMapping("/{userId}/event-registrations")
    public ResponseEntity<?> getEventRegistrationsByUserId(@PathVariable("userId") Long userId) {
        List<EventDTO> eventRegistrations = eventRegistrationService.getAllEventsRegisteredByUser(userId);
        return ResponseEntity.ok(eventRegistrations);
    }

    @DeleteMapping("/{userId}/event-registrations/{eventId}")
    public ResponseEntity<?> cancelRegistration(
            @PathVariable("userId") Long userId, @PathVariable("eventId") Long eventId) {
        EventRegistrationDTO eventRegistrationDTO = eventRegistrationService.cancelRegistration(userId, eventId);
        return ResponseEntity.ok(eventRegistrationDTO);
    }

}
