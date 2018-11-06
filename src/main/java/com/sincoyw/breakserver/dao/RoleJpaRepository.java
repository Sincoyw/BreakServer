package com.sincoyw.breakserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;

public interface RoleJpaRepository extends JpaRepository<Role, Long> {
    Role findByName(@NotNull String name);
}
