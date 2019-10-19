package com.bushemi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class DefaultController {
    private DefaultController() {
    }

    @GetMapping(path = {"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/404")
    public String pageNotFound() {
        return "404";
    }

    @GetMapping("/navigation")
    public String navigation() {
        return "navigation";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
