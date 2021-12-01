package com.bilgeadam.bootcamp.repository;

import com.bilgeadam.bootcamp.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findAllByIsActive(boolean isActive);
}
