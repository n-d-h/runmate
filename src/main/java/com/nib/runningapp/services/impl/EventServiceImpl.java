package com.nib.runningapp.services.impl;

import com.nib.runningapp.services.EventService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
}
