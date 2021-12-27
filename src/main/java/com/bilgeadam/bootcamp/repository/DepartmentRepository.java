package com.bilgeadam.bootcamp.repository;

import com.bilgeadam.bootcamp.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findAllByIsActive(boolean isActive);
    List<Department> findAllByFacultyIdAndIsActive(Long facultyId,boolean isActive);
}
