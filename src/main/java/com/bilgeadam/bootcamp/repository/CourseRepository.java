package com.bilgeadam.bootcamp.repository;

import com.bilgeadam.bootcamp.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByIsActive(boolean isActive);
    List<Course> findAllByIsApproved(boolean isApproved);
}
