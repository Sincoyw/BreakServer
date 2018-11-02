package com.sincoyw.breakserver.controller;

import com.sincoyw.breakserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewController {
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/", "/index", "/home"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new com.sincoyw.breakserver.model.User());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSubmit(@ModelAttribute com.sincoyw.breakserver.model.User user, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "";
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
    public String signUp(Model model) {
        model.addAttribute("user", new com.sincoyw.breakserver.model.User());
        return "sign_up";
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public String signUpSubmit(@ModelAttribute com.sincoyw.breakserver.model.User user, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if (userService.findUserByEmail(user.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/login";
        }
        userService.save(user);
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:/index";
    }
}
