package com.bilgeadam.bootcamp.repository;

import com.bilgeadam.bootcamp.models.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester,Long> {
    List<Semester> findAllByIsActive(boolean isActive);
}
