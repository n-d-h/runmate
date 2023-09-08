package com.nib.runningapp.services.impl;

import com.nib.runningapp.dtos.CourseDTO;
import com.nib.runningapp.entities.Course;
import com.nib.runningapp.entities.Subscription;
import com.nib.runningapp.mappers.CourseMapper;
import com.nib.runningapp.repositories.CourseRepository;
import com.nib.runningapp.repositories.SubscriptionRepository;
import com.nib.runningapp.services.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = CourseMapper.INSTANCE.toEntity(courseDTO);
        course.setStatus(true);
        Course newCourse = courseRepository.save(course);
        return CourseMapper.INSTANCE.toDTO(newCourse);
    }

    @Override
    public List<CourseDTO> getAllCourse() {
        List<Course> courses = courseRepository.findAllActiveCourses();
        return courses.stream().map(CourseMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    @Override
    public CourseDTO updateCourse(CourseDTO courseDTO, Long id) {
        Course existCourse = courseRepository.findById(id).orElse(null);
        Subscription subscription = subscriptionRepository.findById(courseDTO.getSubscriptionId()).orElse(null);
        if(existCourse!=null){
            existCourse.setName(courseDTO.getName());
            existCourse.setDescription(courseDTO.getDescription());
            existCourse.setVideoUrl(courseDTO.getVideoUrl());
            existCourse.setCourseSubscription(subscription);

            Course updateCourse = courseRepository.save(existCourse);
            return CourseMapper.INSTANCE.toDTO(updateCourse);
        }
        return null;
    }

    @Override
    public CourseDTO deleteCourse(Long id) {
        Course existCourse = courseRepository.findById(id).orElse(null);
        if(existCourse==null) return null;

        existCourse.setStatus(false);
        Course deletedCourse = courseRepository.save(existCourse);
        return CourseMapper.INSTANCE.toDTO(deletedCourse);
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        return CourseMapper.INSTANCE.toDTO(course);
    }
}
