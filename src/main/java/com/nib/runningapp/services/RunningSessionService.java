package com.nib.runningapp.services;

import java.util.List;

import com.nib.runningapp.dtos.RunningSessionDTO;

public interface RunningSessionService {
    List<RunningSessionDTO> getAllRunningSessionsByUserId(Long userId);
    RunningSessionDTO createRunningSession(RunningSessionDTO runningSessionDTO);
    RunningSessionDTO updateRunningSession(RunningSessionDTO runningSessionDTO, Long id);
    RunningSessionDTO deleteRunningSession(Long id);
}
