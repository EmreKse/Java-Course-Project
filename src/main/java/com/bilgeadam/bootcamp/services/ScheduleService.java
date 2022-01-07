package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.payload.request.ScheduleRequest;
import com.bilgeadam.bootcamp.payload.response.ScheduledCourseResponse;
import com.bilgeadam.bootcamp.payload.response.ScheduleResponse;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse assignCourseToSchedule(ScheduleRequest scheduleRequest);
    List<ScheduledCourseResponse> getOpenCourses();

    ScheduledCourseResponse registerToCourse(Long courseId);
}

