package com.MBARI.controller;

import com.MBARI.dto.RovDiveDto;
import com.MBARI.service.RovDiveService;
import com.MBARI.utils.MessageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.1")
public class RovDiveController {
    RovDiveService rovDiveService;

    public RovDiveController(RovDiveService rovDiveService) {
        this.rovDiveService = rovDiveService;
    }

    @PostMapping("/rovDive")
    public ResponseEntity<String> addDive(@RequestBody RovDiveDto rovDiveDto) {
        String result = rovDiveService.addDive(rovDiveDto);
        if (MessageUtils.ROV_DIVE_ADDED_SUCCESSFULLY.equals(result)) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
