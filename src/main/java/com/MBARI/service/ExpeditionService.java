package com.MBARI.service;

import com.MBARI.dto.*;
import com.MBARI.entity.ExpeditionEntity;
import com.MBARI.entity.RovDiveEntity;
import com.MBARI.entity.ShipEntity;
import com.MBARI.entity.UserEntity;
import com.MBARI.repository.ExpeditionRepository;
import com.MBARI.repository.ShipRepository;
import com.MBARI.repository.UserRepository;
import com.MBARI.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpeditionService {

    private ShipRepository shipRepository;
    private UserRepository userRepository;
    private ExpeditionRepository expeditionRepository;

    @Autowired
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
        expeditionEntity.setIsPreApproved(pre.getIsPreApproved());

        expeditionRepository.save(expeditionEntity);
        return MessageUtils.EXPEDITION_ADDED_SUCCESSFULLY;
    }

    public List<UnApprovedPreExpeditionDto> getUnApprovedPreExpeditions() {
        List<ExpeditionEntity> expeditionEntities = expeditionRepository.findAll();
        List<UnApprovedPreExpeditionDto> unApprovedPreExpeditionDtos = new ArrayList<>();
        for (ExpeditionEntity expeditionEntity: expeditionEntities) {
            if (!expeditionEntity.getIsPreApproved()) {
                UnApprovedPreExpeditionDto dto = new UnApprovedPreExpeditionDto();
                dto.setExpeditionId(expeditionEntity.getExpeditionId());
                dto.setShipId(expeditionEntity.getShip() != null ? expeditionEntity.getShip().getShipId() : null);
                dto.setChiefScientistId(expeditionEntity.getChiefScientist() != null ? expeditionEntity.getChiefScientist().getUserId() : null);
                dto.setPrincipalInvestigatorId(expeditionEntity.getPrincipalInvestigator() != null ? expeditionEntity.getPrincipalInvestigator().getUserId() : null);
                dto.setPurpose(expeditionEntity.getPurpose());
                dto.setScheduledStartDate(expeditionEntity.getScheduledStartDate());
                dto.setScheduledEndDate(expeditionEntity.getScheduledEndDate());
                dto.setEquipmentDescription(expeditionEntity.getEquipmentDescription());
                dto.setParticipants(expeditionEntity.getParticipants());
                dto.setRegionDescription(expeditionEntity.getRegionDescription());
                dto.setPlannedTrackDescription(expeditionEntity.getPlannedTrackDescription());

                unApprovedPreExpeditionDtos.add(dto);
            }
        }
        return unApprovedPreExpeditionDtos;
    }

    @Transactional
    public String approvePreExpedition(Integer expeditionId) {
        ExpeditionEntity expedition = expeditionRepository.findById(expeditionId).orElseThrow(null);
        if (expedition == null) return MessageUtils.NO_EXPEDITION_DATA;
        expedition.setIsPreApproved(true);
        expeditionRepository.save(expedition);
        return MessageUtils.EXPEDITION_MODIFIED_SUCCESSFULLY;
    }

    public List<ApprovedPreExpeditionDto> getAllApprovedPreExpeditions() {
        List<ExpeditionEntity> expeditionEntities = expeditionRepository.findAll();
        List<ApprovedPreExpeditionDto> approvedPreExpeditionDtos = new ArrayList<>();
        for (ExpeditionEntity expeditionEntity: expeditionEntities) {
            if (expeditionEntity.getIsPreApproved() && expeditionEntity.getActualStartDate() == null) {
                ApprovedPreExpeditionDto dto = new ApprovedPreExpeditionDto();
                dto.setExpeditionId(expeditionEntity.getExpeditionId());
                dto.setShipName(expeditionEntity.getShip().getShipName());
                dto.setChiefScientistName(expeditionEntity.getChiefScientist().getFirstName() + " " + expeditionEntity.getChiefScientist().getLastName());
                dto.setPrincipalInvestigatorName(expeditionEntity.getPrincipalInvestigator().getFirstName() + " " + expeditionEntity.getChiefScientist().getLastName());
                dto.setPurpose(expeditionEntity.getPurpose());
                dto.setScheduledStartDate(expeditionEntity.getScheduledStartDate());
                dto.setScheduledEndDate(expeditionEntity.getScheduledEndDate());
                dto.setEquipmentDescription(expeditionEntity.getEquipmentDescription());
                dto.setParticipants(expeditionEntity.getParticipants());
                dto.setRegionDescription(expeditionEntity.getRegionDescription());
                dto.setPlannedTrackDescription(expeditionEntity.getPlannedTrackDescription());

                approvedPreExpeditionDtos.add(dto);
            }
        }
        return approvedPreExpeditionDtos;
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

    public List<ExpeditionDto> getAllPostExpeditions() {
        List<ExpeditionEntity> expeditionEntities = expeditionRepository.findAll();
        List<ExpeditionDto> expeditionDtos = new ArrayList<>();
        for (ExpeditionEntity expeditionEntity : expeditionEntities) {
            if (expeditionEntity.getIsPreApproved() && expeditionEntity.getActualStartDate() != null) {
                ExpeditionDto dto = new ExpeditionDto();
                dto.setExpeditionId(expeditionEntity.getExpeditionId());
                dto.setShipName(expeditionEntity.getShip().getShipName());
                dto.setChiefScientistName(expeditionEntity.getChiefScientist().getFirstName() + " " + expeditionEntity.getChiefScientist().getLastName());
                dto.setPrincipalInvestigatorName(expeditionEntity.getPrincipalInvestigator().getFirstName() + " " + expeditionEntity.getChiefScientist().getLastName());
                dto.setPurpose(expeditionEntity.getPurpose());
                dto.setScheduledStartDate(expeditionEntity.getScheduledStartDate());
                dto.setScheduledEndDate(expeditionEntity.getScheduledEndDate());
                dto.setEquipmentDescription(expeditionEntity.getEquipmentDescription());
                dto.setParticipants(expeditionEntity.getParticipants());
                dto.setRegionDescription(expeditionEntity.getRegionDescription());
                dto.setPlannedTrackDescription(expeditionEntity.getPlannedTrackDescription());
                dto.setActualStartDate(expeditionEntity.getActualStartDate());
                dto.setActualEndDate(expeditionEntity.getActualEndDate());
                dto.setAccomplishments(expeditionEntity.getAccomplishments());
                dto.setScientistComments(expeditionEntity.getScientistComments());
                dto.setSciObjectivesMet(expeditionEntity.getSciObjectivesMet());
                dto.setOperatorComments(expeditionEntity.getOperatorComments());
                dto.setAllEquipmentFunctioned(expeditionEntity.getAllEquipmentFunctioned());
                dto.setOtherComments(expeditionEntity.getOtherComments());

                expeditionDtos.add(dto);
            }
        }
        return expeditionDtos;
    }

    @Transactional
    public List<ResponseExpeditionDiveDto> searchExpeditions(SearchDto searchDto) {
        List<ExpeditionEntity> expeditionEntities = expeditionRepository.search(searchDto);

        if (expeditionEntities.isEmpty()) return new ArrayList<>();

        List<ResponseExpeditionDiveDto> responseList = new ArrayList<>();

        for (ExpeditionEntity expeditionEntity : expeditionEntities) {
            ResponseExpeditionDiveDto dto = new ResponseExpeditionDiveDto();
            dto.setExpeditionId(expeditionEntity.getExpeditionId());
            dto.setShipName(expeditionEntity.getShip().getShipName());
            dto.setExpeditionChiefScientistName(expeditionEntity.getChiefScientist().getFirstName() + " " + expeditionEntity.getChiefScientist().getLastName());
            dto.setPrincipalInvestigatorName(expeditionEntity.getPrincipalInvestigator().getFirstName() + " " + expeditionEntity.getPrincipalInvestigator().getLastName());
            dto.setPurpose(expeditionEntity.getPurpose());
            dto.setScheduledStartDate(expeditionEntity.getScheduledStartDate());
            dto.setScheduledEndDate(expeditionEntity.getScheduledEndDate());
            dto.setEquipmentDescription(expeditionEntity.getEquipmentDescription());
            dto.setParticipants(expeditionEntity.getParticipants());
            dto.setRegionDescription(expeditionEntity.getRegionDescription());
            dto.setPlannedTrackDescription(expeditionEntity.getPlannedTrackDescription());
            dto.setActualStartDate(expeditionEntity.getActualStartDate());
            dto.setActualEndDate(expeditionEntity.getActualEndDate());
            dto.setAccomplishments(expeditionEntity.getAccomplishments());
            dto.setScientistComments(expeditionEntity.getScientistComments());
            dto.setSciObjectivesMet(expeditionEntity.getSciObjectivesMet());
            dto.setOperatorComments(expeditionEntity.getOperatorComments());
            dto.setAllEquipmentFunctioned(expeditionEntity.getAllEquipmentFunctioned());
            dto.setOtherComments(expeditionEntity.getOtherComments());
            if (expeditionEntity.getUpdatedBy() != null) {
                dto.setUpdatedByUserName(expeditionEntity.getUpdatedBy().getFirstName() + " " + expeditionEntity.getUpdatedBy().getLastName());
            }

            List<ResponseRovDiveDto> rovDiveDtos = new ArrayList<>();
            for (RovDiveEntity rovDiveEntity : expeditionEntity.getRovDives()) {
                ResponseRovDiveDto responseRovDiveDto = new ResponseRovDiveDto();
                responseRovDiveDto.setRovName(rovDiveEntity.getRovName());
                responseRovDiveDto.setDiveNumber(rovDiveEntity.getDiveNumber());
                responseRovDiveDto.setDiveStartDatetime(rovDiveEntity.getDiveStartDatetime());
                responseRovDiveDto.setDiveEndDatetime(rovDiveEntity.getDiveEndDatetime());
                responseRovDiveDto.setDiveChiefScientistName(rovDiveEntity.getDiveChiefScientist().getFirstName() + " " + rovDiveEntity.getDiveChiefScientist().getLastName());
                responseRovDiveDto.setBriefAccomplishments(rovDiveEntity.getBriefAccomplishments());

                rovDiveDtos.add(responseRovDiveDto);
            }

            dto.setRovDives(rovDiveDtos);
            responseList.add(dto);
        }
        return responseList;
    }
}
