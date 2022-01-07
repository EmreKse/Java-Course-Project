package com.bilgeadam.bootcamp.repository;

import com.bilgeadam.bootcamp.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllBySemesterAndCourse_Department(Semester semester, Department department);
    List<Schedule> findAllByCourse_Students (User student);
}
