package com.bilgeadam.bootcamp.repository;

import com.bilgeadam.bootcamp.models.Course;
import com.bilgeadam.bootcamp.models.Department;
import com.bilgeadam.bootcamp.models.Schedule;
import com.bilgeadam.bootcamp.models.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllBySemesterAndCourse_Department(Semester semester, Department department);
}
