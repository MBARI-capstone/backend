package com.MBARI.controller;

import com.MBARI.dto.PreExpeditionDto;
import com.MBARI.dto.PostExpeditionDto;
import com.MBARI.entity.ExpeditionEntity;
import com.MBARI.entity.Ship;
import com.MBARI.repository.ExpeditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1.1")
public class ExpeditionController {

    @Autowired
    ExpeditionRepository ExpeditionRepository;
    ExpeditionEntity ExpeditionEntity;
    @PostMapping("/test")
    public void TestRequest() {
        System.out.println("Testing PreExpedition Controller.");
    }

    @PostMapping("/preexpedition")
    public ExpeditionEntity AddPreExpeditionRequest(  @RequestBody PreExpeditionDto ex){
            ExpeditionEntity newEx = new ExpeditionEntity();

            Ship ship = shipRepostiory.findById(ex.getShipId());

            newEx.setShipId(ex.getShipId());
            newEx.setPurpose(ex.getPurpose());
            newEx.setChiefScientist(ex.getChiefScientist());
            newEx.setPrincipalInvestigator(ex.getPrincipalInvestigator());
            newEx.setScheduledStartDatetime(ex.getScheduledStartDatetime());
            newEx.setScheduledEndDatetime(ex.getScheduledEndDatetime());
            newEx.setEquipmentDescription(ex.getEquipmentDescription());
            newEx.setParticipants(ex.getParticipants());
            newEx.setRegionDescription(ex.getRegionDescription());
            newEx.setPlannedTrackDescription(ex.getPlannedTrackDescription());

           ExpeditionEntity savedExped = ExpeditionRepository.save(newEx);

           return savedExped;
    }
    @PostMapping("/postexpedition")
    public ExpeditionEntity AddPostExpeditionReport(@RequestBody PostExpeditionDto ex){
        ExpeditionEntity newEx = new ExpeditionEntity();

        newEx.setActualStartDatetime(ex.getActualStartTime());
        newEx.setActualEndDatetime(ex.getActualEndDatetime());
        newEx.setAccomplishments(ex.getAccomplishments());
        newEx.setScientistComments(ex.getScientistComments());
        newEx.setSciObjectivesMet(ex.getSciObjectivesMet());
        newEx.setOperatorComments(ex.getOperatorComments());
        newEx.setAllEquipmentFunctioned(ex.getAllEquipmentFunctioned());
        newEx.setOtherComments(ex.getOtherComments());
        newEx.setUpdatedBy(ex.getUpdatedBy());
    }
//    @GetMapping("/expedition_request")
//    public ExpeditionDto RetrievePreExpeditionRequest(@RequestParam("expeditionId") Integer id){
//        //ExpeditionRepository.findbyID();
//        return null;
//    }
}
