package com.MBARI.controller;

import com.MBARI.dto.PreExpeditionDto;
import com.MBARI.dto.PostExpeditionDto;
import com.MBARI.entity.ExpeditionEntity;
import com.MBARI.entity.ShipEntity;
import com.MBARI.entity.UserEntity;
import com.MBARI.repository.ExpeditionRepository;
import com.MBARI.repository.UserRepository;
import com.MBARI.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/v1.1")
public class ExpeditionController {

    ExpeditionRepository expeditionRepository;
    ShipRepository shipRepository;
    UserRepository userRepository;

    @Autowired
    public ExpeditionController(ExpeditionRepository expeditionRepository, ShipRepository shipRepository, UserRepository userRepository) {
        this.expeditionRepository = expeditionRepository;
        this.shipRepository = shipRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/preExpedition")
    @Transactional
    public ResponseEntity<String> AddPreExpeditionRequest(@RequestBody PreExpeditionDto pre) {
        ExpeditionEntity expeditionEntity = new ExpeditionEntity();

        ShipEntity ship = shipRepository.findById(pre.getShipId()).orElse(null);
        if (ship == null) return new ResponseEntity<>("No ship data", HttpStatus.BAD_REQUEST);
        expeditionEntity.setShip(ship);

        expeditionEntity.setPurpose(pre.getPurpose());

        UserEntity chiefScientist = userRepository.findById(pre.getChiefScientistId()).orElse(null);
        if (chiefScientist == null) return new ResponseEntity<>("No chief scientist data", HttpStatus.BAD_REQUEST);
        expeditionEntity.setChiefScientist(chiefScientist);

        UserEntity principalInvestigator = userRepository.findById(pre.getPrincipalInvestigatorId()).orElse(null);
        if (principalInvestigator == null) return new ResponseEntity<>("No principal investigator data", HttpStatus.BAD_REQUEST);
        expeditionEntity.setPrincipalInvestigator(principalInvestigator);

        expeditionEntity.setScheduledStartDate(pre.getScheduledStartDate());
        expeditionEntity.setScheduledEndDate(pre.getScheduledEndDate());
        expeditionEntity.setEquipmentDescription(pre.getEquipmentDescription());
        expeditionEntity.setParticipants(pre.getParticipants());
        expeditionEntity.setRegionDescription(pre.getRegionDescription());
        expeditionEntity.setPlannedTrackDescription(pre.getPlannedTrackDescription());

        expeditionRepository.save(expeditionEntity);
        return new ResponseEntity<>("Expedition added successfully", HttpStatus.OK);
    }

    @PostMapping("/postexpedition")
    public ExpeditionEntity AddPostExpeditionReport(@RequestBody PostExpeditionDto ex){
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
