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

import com.nib.runningapp.dtos.RunningSessionDTO;
import com.nib.runningapp.services.RunningSessionService;

@Tag(name = "User API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final RunningSessionService runningSessionService;

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
}
