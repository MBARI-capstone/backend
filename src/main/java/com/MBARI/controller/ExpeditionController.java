package com.MBARI.controller;

import com.MBARI.dto.*;
import com.MBARI.service.ExpeditionService;
import com.MBARI.utils.MessageUtils;
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

    @GetMapping("/preExpedition/unapproved")
    public ResponseEntity<List<UnApprovedPreExpeditionDto>> unapprovedPreExpedition() {
        List<UnApprovedPreExpeditionDto> expeditionDtos = expeditionService.getUnApprovedPreExpeditions();
        return new ResponseEntity<>(expeditionDtos, HttpStatus.OK);
    }

    @PostMapping("/preExpedition/approve")
    public ResponseEntity<?> approveExpedition(@RequestBody Integer expeditionId) {
        String result = expeditionService.approvePreExpedition(expeditionId);
        if (MessageUtils.EXPEDITION_MODIFIED_SUCCESSFULLY.equals(result)) {
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
