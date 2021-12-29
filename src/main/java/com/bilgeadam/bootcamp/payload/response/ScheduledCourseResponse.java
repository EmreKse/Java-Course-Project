package com.bilgeadam.bootcamp.payload.response;

import com.bilgeadam.bootcamp.models.Course;
import com.bilgeadam.bootcamp.models.Schedule;
import lombok.Builder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public class ScheduledCourseResponse {

    private Long courseId;
    private String courseName;
    private Long instructorId;
    private String instructorName;
    private List<ScheduleResponse> scheduleResponses;

    public ScheduledCourseResponse(Course course, List<Schedule> schedules) {
        this.courseId = course.getId();
        this.courseName = course.getName();
        this.instructorId = course.getId();
        this.instructorName = schedules.get(0).getInstructor().getUsername();
        this.scheduleResponses = schedules.stream().map(ScheduleResponse::new).collect(Collectors.toList());

    }
}
