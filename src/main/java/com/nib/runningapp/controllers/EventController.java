package com.nib.runningapp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nib.runningapp.dtos.EventDTO;
import com.nib.runningapp.services.EventService;

@Tag(name = "Event API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    @GetMapping("")
    public ResponseEntity<?> getAllEvents() {
        List<EventDTO> eventDTOs = eventService.getAllEvents();
        return ResponseEntity.ok(eventDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable("id") Long id) {
        EventDTO eventDTO = eventService.getEventById(id);
        return ResponseEntity.ok(eventDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> createEvent(@RequestBody EventDTO eventDTO) {
        EventDTO savedEventDTO = eventService.createEvent(eventDTO);
        return ResponseEntity.created(null).body(savedEventDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@RequestBody EventDTO eventDTO, @PathVariable("id") Long id) {
        EventDTO updatedEventDTO = eventService.updateEvent(eventDTO, id);
        return ResponseEntity.ok(updatedEventDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") Long id) {
        EventDTO deletedEventDTO = eventService.deleteEvent(id);
        return ResponseEntity.ok(deletedEventDTO);
    }

}
