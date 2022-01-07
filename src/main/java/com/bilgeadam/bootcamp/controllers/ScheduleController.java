package com.bilgeadam.bootcamp.controllers;

import com.bilgeadam.bootcamp.payload.request.ScheduleRequest;
import com.bilgeadam.bootcamp.services.ScheduleService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PreAuthorize("hasRole('DEAN')")
    @PostMapping("/")
    public ResponseEntity<?> assignScheduleToCourse (@Valid @RequestBody ScheduleRequest scheduleRequest) {
        return ResponseEntity.ok(scheduleService.assignCourseToSchedule(scheduleRequest));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/users/open_courses")
    public ResponseEntity<?> getOpenCourses () {
        return ResponseEntity.ok(scheduleService.getOpenCourses());
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PutMapping("/users/register_course/{courseId}")
    public ResponseEntity<?> registerToCourse (@PathVariable Long courseId ) {
        return ResponseEntity.ok(scheduleService.registerToCourse(courseId));
    }

}


