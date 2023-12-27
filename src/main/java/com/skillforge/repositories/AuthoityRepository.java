package com.skillforge.repositories;

import com.skillforge.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthoityRepository extends JpaRepository<Authority, Integer> {
    Optional<Authority> findByName(String name);
}