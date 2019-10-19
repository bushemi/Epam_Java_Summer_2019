package com.bushemi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class TestProcessingController {

    @GetMapping("/testing")
    public String testing() {
        return "testing";
    }

    @GetMapping("/testInProcess")
    public String testInProcess() {
        return "testInProcess";
    }

    @GetMapping("/testResult")
    public String testResult() {
        return "testResult";
    }
}
