package com.sincoyw.breakserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    User findByEmail(@NotNull String email);
}
