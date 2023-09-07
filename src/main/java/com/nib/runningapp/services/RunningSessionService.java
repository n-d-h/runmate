package com.nib.runningapp.services;

import java.util.List;

import com.nib.runningapp.dtos.RunningSessionDTO;

public interface RunningSessionService {
    List<RunningSessionDTO> getAllRunningSessionsByUserId(Long id);
    RunningSessionDTO createRunningSession(RunningSessionDTO runningSessionDTO);
    RunningSessionDTO updateRunningSession(RunningSessionDTO runningSessionDTO);
    RunningSessionDTO deleteRunningSession(Long id);
}
