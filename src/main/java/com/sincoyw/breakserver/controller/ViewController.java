package com.sincoyw.breakserver.controller;

import com.sincoyw.breakserver.dao.User;
import com.sincoyw.breakserver.dao.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewController {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @RequestMapping(value = {"/", "/index", "/home"})
    public String index() {
        return "index";
    }

    @GetMapping(value = "/user/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userJpaRepository.findAll();
    }
}