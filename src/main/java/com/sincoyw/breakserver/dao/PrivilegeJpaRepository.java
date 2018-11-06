package com.sincoyw.breakserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;

public interface PrivilegeJpaRepository extends JpaRepository<Privilege, Long> {
    Privilege findByName(@NotNull String name);
}
