package com.sincoyw.breakserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;

public interface UserJpaRepository extends JpaRepository<User, String> {
    User findByEmail(@NotNull String email);
    User findByUserName(@NotNull String userName);
}
