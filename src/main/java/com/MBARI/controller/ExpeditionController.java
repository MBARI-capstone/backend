package com.MBARI.controller;

import com.MBARI.dto.PreExpeditionDto;
import com.MBARI.dto.PostExpeditionDto;
import com.MBARI.entity.ExpeditionEntity;
import com.MBARI.service.ExpeditionService;
import com.MBARI.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/postexpedition")
    public ExpeditionEntity addPostExpeditionReport(@RequestBody PostExpeditionDto ex){
        ExpeditionEntity newEx = new ExpeditionEntity();

        //LocalDateTime actualStart = new
//        newEx.setActualStartDatetime(ex.getActualStartTime());
      //  newEx.setActualEndDatetime(ex.getActualEndDatetime());
        newEx.setAccomplishments(ex.getAccomplishments());
        newEx.setScientistComments(ex.getScientistComments());
       // newEx.setSciObjectivesMet(ex.getSciObjectivesMet());
        newEx.setOperatorComments(ex.getOperatorComments());
        newEx.setAllEquipmentFunctioned(ex.getAllEquipmentFunctioned());
        newEx.setOtherComments(ex.getOtherComments());
        return null;
      //  newEx.setUpdatedBy(ex.getUpdatedBy());
    }
//    @GetMapping("/expedition_request")
//    public ExpeditionDto RetrievePreExpeditionRequest(@RequestParam("expeditionId") Integer id){
//        //ExpeditionRepository.findbyID();
//        return null;
//    }


}
