package com.bushemi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class TestController {

    @GetMapping("/addTest")
    public String addTest() {
        return "addTest";
    }

    @GetMapping("/editTest")
    public String editTest() {
        return "editTest";
    }

    @GetMapping("/tests")
    public String tests() {
        return "tests";
    }

}
