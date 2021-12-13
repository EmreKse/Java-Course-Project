package com.bilgeadam.bootcamp.repository;

import com.bilgeadam.bootcamp.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findAllByIsActive(boolean isActive);
    List<Department> findAllByFacultyIdAndIsActive(Long facultyId,boolean isActive);
}
