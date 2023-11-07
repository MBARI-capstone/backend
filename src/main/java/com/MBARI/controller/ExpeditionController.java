package com.MBARI.controller;

import com.MBARI.dto.PreExpeditionDto;
import com.MBARI.dto.PostExpeditionDto;
import com.MBARI.dto.ResponseExpeditionDiveDto;
import com.MBARI.dto.SearchDto;
import com.MBARI.service.ExpeditionService;
import com.MBARI.utils.MessageUtils;
import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.1")
public class ExpeditionController {

    private ExpeditionService expeditionService;

    @Autowired
    public ExpeditionController(ExpeditionService expeditionService) {
        this.expeditionService = expeditionService;
    }

    @PostMapping("/preExpedition")
    public ResponseEntity<String> addPreExpeditionRequest(@RequestBody PreExpeditionDto pre) {
        String result = expeditionService.addPreExpedition(pre);
        if (MessageUtils.EXPEDITION_ADDED_SUCCESSFULLY.equals(result)) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/postExpedition")
    public ResponseEntity<String> addPostExpeditionReport(@RequestBody PostExpeditionDto post){
        String result = expeditionService.addPostExpedition(post);
        if (MessageUtils.EXPEDITION_ADDED_SUCCESSFULLY.equals(result)) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchExpeditions(@RequestBody SearchDto searchDto) {
        List<ResponseExpeditionDiveDto> result = expeditionService.searchExpeditions(searchDto);
        if (result == null) return new ResponseEntity<>(MessageUtils.NO_EXPEDITION_DATA, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
