package com.nib.runningapp.services.impl;

import com.nib.runningapp.services.RunningSessionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class RunningSessionServiceImpl implements RunningSessionService {
}
