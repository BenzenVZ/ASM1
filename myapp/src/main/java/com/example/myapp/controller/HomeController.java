package com.example.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = "/home") //để truy cập URL
    public String home() {
        return "redirect:/cert/list";
    }
}
