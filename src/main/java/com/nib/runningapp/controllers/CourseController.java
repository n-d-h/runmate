package com.nib.runningapp.controllers;

import com.nib.runningapp.dtos.CourseDTO;
import com.nib.runningapp.entities.Course;
import com.nib.runningapp.services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Course API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
@SecurityRequirement(name = "Authorization")
public class CourseController {

    private final CourseService courseService;

    @Operation(summary = "Get all courses")
    @GetMapping("")
    public ResponseEntity<?> getAllCourse() {
        List<CourseDTO> courses = courseService.getAllCourse();
        return ResponseEntity.ok(courses);
    }

    @Operation(summary = "Get course by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable("id") Long id){
        CourseDTO courseDTO = courseService.getCourseById(id);
        return ResponseEntity.ok(courseDTO);
    }

    @Operation(summary = "Create course")
    @PostMapping("")
    public ResponseEntity<?> createCourse(@RequestBody CourseDTO courseDTO){
        CourseDTO course = courseService.createCourse(courseDTO);
        return ResponseEntity.created(null).body(course);
    }

    @Operation(summary = "Update course")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@RequestBody CourseDTO courseDTO,
                                          @PathVariable("id") Long id){
        CourseDTO updateCourse = courseService.updateCourse(courseDTO, id);
        return ResponseEntity.ok(updateCourse);
    }

    @Operation(summary = "Delete course")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id){
        CourseDTO deleteCourse = courseService.deleteCourse(id);
        return ResponseEntity.ok(deleteCourse);
    }
}
