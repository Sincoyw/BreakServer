package com.sincoyw.breakserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserJpaRepository extends JpaRepository<User, String> {
    List<User> findByEmail(@NotNull String email);
}
