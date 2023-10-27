package com.MBARI.controller;

import com.MBARI.dto.PreExpeditionDto;
import com.MBARI.dto.PostExpeditionDto;
import com.MBARI.entity.ExpeditionEntity;
import com.MBARI.entity.Ship;
import com.MBARI.entity.UserEntity;
import com.MBARI.repository.ExpeditionRepository;
import com.MBARI.repository.UserRepository;
import com.MBARI.repository.shipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.1")
public class ExpeditionController {

    @Autowired
    ExpeditionRepository ExpeditionRepository;
    ExpeditionEntity ExpeditionEntity;
    shipRepository shipRepository;
    UserRepository userRepository;
    @PostMapping("/test")
    public void TestRequest() {
        System.out.println("Testing PreExpedition Controller.");
    }

    @PostMapping("/preexpedition")
    public ExpeditionEntity AddPreExpeditionRequest(  @RequestBody PreExpeditionDto ex){
            ExpeditionEntity newEx = new ExpeditionEntity();

            Ship ship = shipRepository.findByShipName(ex.getShipName());

            UserEntity chiefSci = userRepository.findByUsername(ex.getUsername());
            UserEntity prinInv = userRepository.findByUsername(ex.getUsername());

            LocalDate start =  LocalDate.of(ex.getStartYear(),ex.getStartMonth(),ex.getStartDay());//,ex.getStartHour(),ex.getStartMin());
            LocalDate end = LocalDate.of(ex.getEndYear(),ex.getEndMonth(),ex.getEndDay());//,ex.getEndHour(),ex.getEndMin());

            //LocalDateTime end = ex.getScheduledStartDatetime();

            newEx.setShipId(ship); //Set Ship

            newEx.setChiefScientist(chiefSci); //setChiefScientist
            newEx.setPrincipalInvestigator(prinInv); //setPrincipalInvestigator

            newEx.setScheduledStartDatetime(start); //Scheduled Start time
            newEx.setScheduledStartDatetime(end); //Scheduled End time

            newEx.setPurpose(ex.getPurpose());
            newEx.setEquipmentDescription(ex.getEquipmentDesc());
            newEx.setParticipants(ex.getParticipants());
            newEx.setRegionDescription(ex.getRegionDesc());
            newEx.setPlannedTrackDescription(ex.getPlannedTrackDesc());

           ExpeditionEntity savedExped = ExpeditionRepository.save(newEx);

           return savedExped;

    }

    @PostMapping("/postexpedition")
    public ExpeditionEntity AddPostExpeditionReport(@RequestBody PostExpeditionDto ex){
        ExpeditionEntity newEx = new ExpeditionEntity();

        //LocalDateTime actualStart = new
        newEx.setActualStartDatetime(ex.getActualStartTime());
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
