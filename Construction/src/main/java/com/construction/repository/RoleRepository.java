package com.construction.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.construction.models.ERole;
import com.construction.models.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
	Optional<Roles> findByName(ERole name);
}