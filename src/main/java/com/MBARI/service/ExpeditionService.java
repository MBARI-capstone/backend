package com.MBARI.service;

import com.MBARI.dto.PostExpeditionDto;
import com.MBARI.dto.PreExpeditionDto;
import com.MBARI.entity.ExpeditionEntity;
import com.MBARI.entity.ShipEntity;
import com.MBARI.entity.UserEntity;
import com.MBARI.repository.ExpeditionRepository;
import com.MBARI.repository.ShipRepository;
import com.MBARI.repository.UserRepository;
import com.MBARI.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ExpeditionService {

    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpeditionRepository expeditionRepository;

    public ExpeditionService(ShipRepository shipRepository, UserRepository userRepository, ExpeditionRepository expeditionRepository) {
        this.shipRepository = shipRepository;
        this.userRepository = userRepository;
        this.expeditionRepository = expeditionRepository;
    }

    @Transactional
    public String addPreExpedition(PreExpeditionDto pre) {
        ExpeditionEntity expeditionEntity = new ExpeditionEntity();

        ShipEntity ship = shipRepository.findById(pre.getShipId()).orElse(null);
        if (ship == null) return MessageUtils.NO_SHIP_DATA;
        expeditionEntity.setShip(ship);

        expeditionEntity.setPurpose(pre.getPurpose());

        UserEntity chiefScientist = userRepository.findById(pre.getChiefScientistId()).orElse(null);
        if (chiefScientist == null) return MessageUtils.NO_CHIEF_SCIENTIST_DATA;
        expeditionEntity.setChiefScientist(chiefScientist);

        UserEntity principalInvestigator = userRepository.findById(pre.getPrincipalInvestigatorId()).orElse(null);
        if (principalInvestigator == null) return MessageUtils.NO_PRINCIPAL_INVESTIGATOR_DATA;
        expeditionEntity.setPrincipalInvestigator(principalInvestigator);

        expeditionEntity.setScheduledStartDate(pre.getScheduledStartDate());
        expeditionEntity.setScheduledEndDate(pre.getScheduledEndDate());
        expeditionEntity.setEquipmentDescription(pre.getEquipmentDescription());
        expeditionEntity.setParticipants(pre.getParticipants());
        expeditionEntity.setRegionDescription(pre.getRegionDescription());
        expeditionEntity.setPlannedTrackDescription(pre.getPlannedTrackDescription());

        expeditionRepository.save(expeditionEntity);
        return MessageUtils.EXPEDITION_ADDED_SUCCESSFULLY;
    }
    @Transactional
    public String addPostExpedition(PostExpeditionDto post) {
        ExpeditionEntity expeditionEntity = expeditionRepository.findById(post.getExpeditionId()).orElse(null);
        if (expeditionEntity == null) return MessageUtils.NO_EXPEDITION_DATA;

        expeditionEntity.setActualStartDate(post.getActualStartDate());
        expeditionEntity.setActualEndDate(post.getActualEndDate());
        expeditionEntity.setAccomplishments(post.getAccomplishments());
        expeditionEntity.setScientistComments(post.getScientistComments());
        expeditionEntity.setSciObjectivesMet(post.getSciObjectivesMet());
        expeditionEntity.setOperatorComments(post.getOperatorComments());
        expeditionEntity.setAllEquipmentFunctioned(post.getAllEquipmentFunctioned());
        expeditionEntity.setOtherComments(post.getOtherComments());

        UserEntity updatedBy = userRepository.findById(post.getUpdatedBy()).orElse(null);
        if (updatedBy == null) return MessageUtils.NO_UPDATED_BY;
        expeditionEntity.setUpdatedBy(updatedBy);

        expeditionRepository.save(expeditionEntity);

        return MessageUtils.EXPEDITION_ADDED_SUCCESSFULLY;
    }
}
