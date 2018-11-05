package com.sincoyw.breakserver.service;

import com.sincoyw.breakserver.dao.UserJpaRepository;
import com.sincoyw.breakserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Repository
public class UserServiceImpl implements UserService {

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
        tempUser.setUserName(user.getUsername());
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
        com.sincoyw.breakserver.dao.User user = userJpaRepository.findByEmail(email);
        return convertFromDaoToModelUser(user);
    }


    public User convertFromDaoToModelUser(com.sincoyw.breakserver.dao.User user) {
        if (null != user) {
            User tempUser = new User();
            tempUser.setUserID(user.getUserID());
            tempUser.setEmail(user.getEmail());
            tempUser.setPassword(user.getPassword());
            tempUser.setUsername(user.getUserName());
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
        }
        return null;
    }
}
