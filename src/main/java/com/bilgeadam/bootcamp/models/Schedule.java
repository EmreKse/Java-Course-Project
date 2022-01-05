package com.bilgeadam.bootcamp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotEmpty
    @ManyToOne
    @JoinColumn(name="course_id", nullable = false)
    private Course course;

//    @NotBlank
    @ManyToOne
    @JoinColumn(name="instructor_id", nullable = false)
    private User instructor;

//    @NotBlank
    @ManyToOne
    @JoinColumn(name="semester_id", nullable = false)
    private Semester semester;

//    @NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private EnumDay day;

//    @NotNull
    private Long hour;

    public Schedule() {

    }

}
