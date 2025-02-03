package com.demo.ecommerce.user.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.ecommerce.user.domain.Role;

public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByName(String name);
}
