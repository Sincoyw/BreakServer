package com.sincoyw.breakserver.controller;

import com.sincoyw.breakserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ViewController {
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/", "/index", "/home"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
    public String signUp(Model model) {
        model.addAttribute("user", new com.sincoyw.breakserver.model.User());
        return "sign_up";
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public String signUpSubmit(@ModelAttribute com.sincoyw.breakserver.model.User user) {
        userService.save(user);
        return "index";
    }
}
