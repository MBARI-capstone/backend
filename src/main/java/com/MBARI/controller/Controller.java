package com.MBARI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.1")
public class Controller {
    @GetMapping("/test")
    public String test() {
        return "Hello World!";
    }
}
