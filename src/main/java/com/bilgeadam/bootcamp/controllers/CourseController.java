package com.bilgeadam.bootcamp.controllers;

import com.bilgeadam.bootcamp.payload.request.CourseApproveRequest;
import com.bilgeadam.bootcamp.payload.request.CourseInstructorAssignRequest;
import com.bilgeadam.bootcamp.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/course")
public class CourseController {

    final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEAN')")
    @PutMapping("/assign/{courseId}")
    public ResponseEntity<?> assignInstructorToCourse(@PathVariable Long courseId, @Valid @RequestBody CourseInstructorAssignRequest courseInstructorAssignRequest) {
        return ResponseEntity.ok(courseService.assignInstructorToCourse(courseId,courseInstructorAssignRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/request")
    public ResponseEntity<?> listCourseRequests() {
        return ResponseEntity.ok(courseService.listCourseRequests());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/request/{courseId}")
    public  ResponseEntity<?> approveOrRejectCourse(@PathVariable Long courseId, @Valid @RequestBody CourseApproveRequest courseApproveRequest) {
        return  ResponseEntity.ok(courseService.approveOrRejectCourse(courseId,courseApproveRequest));
    }

}

