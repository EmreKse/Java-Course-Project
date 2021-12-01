package com.bilgeadam.bootcamp.repository;

import com.bilgeadam.bootcamp.models.EnumRole;
import com.bilgeadam.bootcamp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(EnumRole name);
}
