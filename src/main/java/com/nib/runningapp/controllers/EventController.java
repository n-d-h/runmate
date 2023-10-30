package com.nib.runningapp.controllers;

import com.nib.runningapp.dtos.EventDTO;
import com.nib.runningapp.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Event API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
//@SecurityRequirement(name = "Authorization")
public class EventController {

    private final EventService eventService;

    @Operation(summary = "Get all events")
    @GetMapping("")
    public ResponseEntity<?> getAllEvents() {
        List<EventDTO> eventDTOs = eventService.getAllEvents();
        return ResponseEntity.ok(eventDTOs);
    }

    @Operation(summary = "Get event by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable("id") Long id) {
        EventDTO eventDTO = eventService.getEventById(id);
        return ResponseEntity.ok(eventDTO);
    }

    @Operation(summary = "Create event")
    @PostMapping("")
    public ResponseEntity<?> createEvent(@RequestBody EventDTO eventDTO) {
        EventDTO savedEventDTO = eventService.createEvent(eventDTO);
        return ResponseEntity.created(null).body(savedEventDTO);
    }

    @Operation(summary = "Update event")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@RequestBody EventDTO eventDTO, @PathVariable("id") Long id) {
        EventDTO updatedEventDTO = eventService.updateEvent(eventDTO, id);
        return ResponseEntity.ok(updatedEventDTO);
    }

    @Operation(summary = "Delete event")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") Long id) {
        EventDTO deletedEventDTO = eventService.deleteEvent(id);
        return ResponseEntity.ok(deletedEventDTO);
    }

}
