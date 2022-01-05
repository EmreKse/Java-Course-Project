package com.bilgeadam.bootcamp.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@Table(name = "semesters",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String name;

    @Column(name="is_active")
    private Boolean isActive = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "semesters_courses",
            joinColumns = @JoinColumn(name = "semester_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;

    public Semester(String name) {
        this.name = name;
    }

    public Semester() {

    }

}
