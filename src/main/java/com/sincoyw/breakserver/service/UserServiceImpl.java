package com.sincoyw.breakserver.service;

import com.sincoyw.breakserver.dao.UserJpaRepository;
import com.sincoyw.breakserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Repository
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        com.sincoyw.breakserver.dao.User tempUser = new com.sincoyw.breakserver.dao.User();
        tempUser.setUserID(user.getUserID());
        tempUser.setEmail(user.getEmail());
        tempUser.setPassword(user.getPassword());
        tempUser.setCountryCode(user.getCountryCode());
        tempUser.setPhone(user.getPhone());
        tempUser.setFirstName(user.getFirstName());
        tempUser.setLastName(user.getLastName());

        Instant instant = user.getBirthday().toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();

        tempUser.setBirthday(java.sql.Date.valueOf(localDate));
        tempUser.setGender(user.getGender());
        userJpaRepository.save(tempUser);
        return user;
    }
}
