package com.bilgeadam.bootcamp.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ScheduleRequest {
    private Long courseId;
    private Long instructorId;
    private String day;
    private Long hour;
}
