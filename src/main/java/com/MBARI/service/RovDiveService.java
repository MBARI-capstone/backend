package com.MBARI.service;

import com.MBARI.dto.RovDiveDto;
import com.MBARI.entity.ExpeditionEntity;
import com.MBARI.entity.RovDiveEntity;
import com.MBARI.entity.UserEntity;
import com.MBARI.repository.ExpeditionRepository;
import com.MBARI.repository.RovDiveRepository;
import com.MBARI.repository.UserRepository;
import com.MBARI.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RovDiveService {
    private RovDiveRepository rovDiveRepository;
    private UserRepository userRepository;
    private ExpeditionRepository expeditionRepository;

    @Autowired
    public RovDiveService(RovDiveRepository rovDiveRepository, UserRepository userRepository, ExpeditionRepository expeditionRepository) {
        this.rovDiveRepository = rovDiveRepository;
        this.userRepository = userRepository;
        this.expeditionRepository = expeditionRepository;
    }

    @Transactional
    public String addDive(RovDiveDto rovDiveDto) {
        RovDiveEntity rovDiveEntity = new RovDiveEntity(rovDiveDto);

        UserEntity chiefScientist = userRepository.findById(rovDiveDto.getDiveChiefScientistId()).orElse(null);
        if (chiefScientist == null) return MessageUtils.NO_CHIEF_SCIENTIST_DATA;
        rovDiveEntity.setDiveChiefScientist(chiefScientist);

        ExpeditionEntity expeditionEntity = expeditionRepository.findById(rovDiveDto.getExpeditionId()).orElse(null);
        if (expeditionEntity == null) return MessageUtils.NO_EXPEDITION_DATA;
        rovDiveEntity.setRelatedExpedition(expeditionEntity);

        rovDiveRepository.save(rovDiveEntity);
        return MessageUtils.ROV_DIVE_ADDED_SUCCESSFULLY;
    }
}
