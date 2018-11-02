package com.sincoyw.breakserver.service;

import com.sincoyw.breakserver.dao.UserJpaRepository;
import com.sincoyw.breakserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Repository
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        com.sincoyw.breakserver.dao.User tempUser = new com.sincoyw.breakserver.dao.User();
        tempUser.setUserID(user.getUserID());
        tempUser.setEmail(user.getEmail());
        tempUser.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public User findUserByEmail(String email) {
        List<com.sincoyw.breakserver.dao.User> userList = userJpaRepository.findByEmail(email);
        if (userList.size() > 0) {
            com.sincoyw.breakserver.dao.User user = userList.get(0);
            User tempUser = new User();
            tempUser.setUserID(user.getUserID());
            tempUser.setEmail(user.getEmail());
            tempUser.setPassword(user.getPassword());
            tempUser.setCountryCode(user.getCountryCode());
            tempUser.setPhone(user.getPhone());
            tempUser.setFirstName(user.getFirstName());
            tempUser.setLastName(user.getLastName());
            LocalDate localDate = user.getBirthday().toLocalDate();
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
            tempUser.setBirthday(java.util.Date.from(instant));
            tempUser.setGender(user.getGender());
            return tempUser;
        } else {
            return null;
        }
    }
}
