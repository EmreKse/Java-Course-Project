package com.bilgeadam.bootcamp.payload.response;

import com.bilgeadam.bootcamp.models.EnumDay;
import com.bilgeadam.bootcamp.models.Schedule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleResponse {

    private Long id;
    private CourseResponse course;
    private UserResponse instructor;
    private SemesterResponse semester;
    private EnumDay day;
    private Long hour;

    public ScheduleResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.course = new CourseResponse(schedule.getCourse());
        this.instructor = new UserResponse(schedule.getInstructor());
        this.semester = new SemesterResponse(schedule.getSemester());
        this.day = schedule.getDay();
        this.hour = schedule.getHour();
    }
}
