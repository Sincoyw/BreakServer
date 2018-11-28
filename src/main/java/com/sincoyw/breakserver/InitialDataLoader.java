package com.sincoyw.breakserver;

import com.sincoyw.breakserver.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 在ContextRefreshedEvent时初始化一些系统环境，如果没有进行权限和初始用户初始化则进行初始化
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    @Autowired
    private PrivilegeJpaRepository privilegeJpaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        if (null == userJpaRepository.findByEmail("test@test.com")) {
            Role adminRole = roleJpaRepository.findByName("ROLE_ADMIN");
            User user = new User();
            user.setFirstName("Test");
            user.setLastName("Test");
            user.setPassword(passwordEncoder.encode("test"));
            user.setEmail("test@test.com");
            user.setUserName("Test");
            user.setCountryCode("86");
            user.setPhone("1111111");
            user.setGender(1);
            user.setRoles(Arrays.asList(adminRole));
            userJpaRepository.save(user);
        } else {
            logger.info("User exist with email test@test.com.");
        }

        alreadySetup = true;
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeJpaRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeJpaRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    public Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = roleJpaRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleJpaRepository.save(role);
        }
        return role;
    }

}
