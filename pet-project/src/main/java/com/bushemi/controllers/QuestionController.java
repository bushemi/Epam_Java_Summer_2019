package com.bushemi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class QuestionController {

    @GetMapping("/question")
    public String question() {
        return "question";
    }
}
