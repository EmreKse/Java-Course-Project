package com.bilgeadam.bootcamp.payload.response;

import com.bilgeadam.bootcamp.models.Course;
import com.bilgeadam.bootcamp.models.Schedule;
import lombok.Builder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public class OpenCourseInfoResponse  {

    private Long id;
    private String name;
    private Long instructorId;
    private String instructorName;
    private String semesterName;
    private Set<Schedule> schedules;

    public OpenCourseInfoResponse(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.instructorId = course.getInstructors().
    }
}
