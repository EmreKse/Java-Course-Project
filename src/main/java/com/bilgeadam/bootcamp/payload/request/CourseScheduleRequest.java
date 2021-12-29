package com.bilgeadam.bootcamp.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CourseScheduleRequest {

    private String day;
    private Long hour;

}
