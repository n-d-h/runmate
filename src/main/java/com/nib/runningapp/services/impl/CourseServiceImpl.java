package com.nib.runningapp.services.impl;

import com.nib.runningapp.services.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
}
