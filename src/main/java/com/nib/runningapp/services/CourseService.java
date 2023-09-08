package com.nib.runningapp.services;

import com.nib.runningapp.dtos.CourseDTO;

import java.util.List;

public interface CourseService {
    CourseDTO createCourse(CourseDTO courseDTO);
    List<CourseDTO> getAllCourse();
    CourseDTO updateCourse(CourseDTO courseDTO, Long id);
    CourseDTO deleteCourse(Long id);
    CourseDTO getCourseById(Long id);
}
