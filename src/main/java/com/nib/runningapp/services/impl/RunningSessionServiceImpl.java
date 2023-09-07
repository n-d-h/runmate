package com.nib.runningapp.services.impl;

import com.nib.runningapp.dtos.RunningSessionDTO;
import com.nib.runningapp.entities.RunningSession;
import com.nib.runningapp.mappers.RunningSessionMapper;
import com.nib.runningapp.repositories.RunningSessionRepository;
import com.nib.runningapp.services.RunningSessionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class RunningSessionServiceImpl implements RunningSessionService {
    
    private final RunningSessionRepository runningSessionRepository;
    
    @Override
    public RunningSessionDTO createRunningSession(RunningSessionDTO runningSessionDTO) {
        runningSessionDTO.setId(0L);
        runningSessionDTO.setDate(new Date());
        runningSessionDTO.setStartTime(new Date());
        runningSessionDTO.setStatus(true);
        RunningSession runningSession = RunningSessionMapper.INSTANCE.toEntity(runningSessionDTO);

        RunningSession savedRunningSession = runningSessionRepository.save(runningSession);
        return RunningSessionMapper.INSTANCE.toDTO(savedRunningSession);
    }

    @Override
    public List<RunningSessionDTO> getAllRunningSessionsByUserId(Long userId) {
        List<RunningSession> runningSessions = runningSessionRepository.findByUserId(userId);
        return runningSessions.stream().map(RunningSessionMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    @Override
    public RunningSessionDTO updateRunningSession(RunningSessionDTO runningSessionDTO, Long id) {
        RunningSession existRunningSession = runningSessionRepository.findById(id).orElse(null);
        if(existRunningSession == null){
            return null;
        }

        RunningSession runningSession = RunningSessionMapper.INSTANCE.toEntity(runningSessionDTO);
        runningSession.setId(id);
        if(runningSession.getStatus() == null){
            runningSession.setStatus(existRunningSession.getStatus());
        }

        RunningSession updatedRunningSession = runningSessionRepository.save(runningSession);
        return RunningSessionMapper.INSTANCE.toDTO(updatedRunningSession);
    }

    @Override
    public RunningSessionDTO deleteRunningSession(Long id) {
        RunningSession runningSession = runningSessionRepository.findById(id).orElse(null);
        if (runningSession == null) {
            return null;
        }
        runningSessionRepository.delete(runningSession);
        return RunningSessionMapper.INSTANCE.toDTO(runningSession);
    }
}
