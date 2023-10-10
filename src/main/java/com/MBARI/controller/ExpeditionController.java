package com.MBARI.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.1")
public class ExpeditionController {
    @PostMapping("/precruise")
    public String test() {
        return null;
    }

}
