package com.bilgeadam.bootcamp.payload.response;

import com.bilgeadam.bootcamp.models.Course;
import com.bilgeadam.bootcamp.models.Semester;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SemesterResponse {
    private Long id;

    private String name;

    private Boolean isActive;

    private Set<Course> courses;

    public SemesterResponse(Semester semester) {
        this.id = semester.getId();
        this.name = semester.getName();
        this.isActive = semester.getActive();
        this.courses = semester.getCourses();

    }

}
