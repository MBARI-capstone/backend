package com.MBARI.repository;

import com.MBARI.entity.ExpeditionEntity;
import com.MBARI.dto.SearchDto;
import com.MBARI.entity.RovDiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.JoinType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface ExpeditionRepository extends JpaRepository<ExpeditionEntity, Integer>, JpaSpecificationExecutor<ExpeditionEntity> {

    default List<ExpeditionEntity> search(SearchDto dto) {
        return findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Join 'rovDiveJoin' with 'Expeditions' to prevent N+1 issues due to lazy loading of rovDives in ExpeditionEntity.
            // In terms of performance it is bad to use FetchType.EAGER because of need to fetch all time even tho do not have to.
            Join<ExpeditionEntity, RovDiveEntity> rovDiveJoin = root.join("rovDives", JoinType.LEFT);

            if (dto.getShipId() != null) {
                predicates.add(cb.equal(root.get("ship").get("shipId"), dto.getShipId()));
            }

            if (dto.getExpeditionChiefScientistId() != null) {
                predicates.add(cb.equal(root.get("chiefScientist").get("userId"), dto.getExpeditionChiefScientistId()));
            }

            if (dto.getPrincipalInvestigatorId() != null) {
                predicates.add(cb.equal(root.get("principalInvestigator").get("userId"), dto.getPrincipalInvestigatorId()));
            }

            if (dto.getExpeditionStartDate() != null) {
                predicates.add(cb.equal(root.get("scheduledStartDate"), dto.getExpeditionStartDate()));
                predicates.add(cb.equal(root.get("actualStartDate"), dto.getExpeditionStartDate()));
            }

            if (dto.getExpeditionEndDate() != null) {
                predicates.add(cb.equal(root.get("scheduledEndDate"), dto.getExpeditionEndDate()));
                predicates.add(cb.equal(root.get("actualEndDate"), dto.getExpeditionEndDate()));
            }

            if (dto.getExpeditionSequenceNumber() != null) {
                predicates.add(cb.equal(root.get("updatedBy").get("userId"), dto.getExpeditionSequenceNumber()));
            }

            if (dto.getSciObjectivesMet() != null) {
                predicates.add(cb.equal(root.get("sciObjectivesMet"), dto.getSciObjectivesMet()));
            }

            if (dto.getAllEquipmentFunctioned() != null) {
                predicates.add(cb.equal(root.get("allEquipmentFunctioned"), dto.getAllEquipmentFunctioned()));
            }

            if (dto.getDiveNumber() != null) {
                Predicate rovDivePredicate = cb.equal(rovDiveJoin.get("diveNumber"), dto.getDiveNumber());
                predicates.add(rovDivePredicate);
            }

            if (dto.getDiveChiefScientistId() != null) {
                Predicate rovDivePredicate = cb.equal(rovDiveJoin.get("diveChiefScientist"), dto.getDiveChiefScientistId());
                predicates.add(rovDivePredicate);
            }

            if (dto.getDiveStartDate() != null) {
                LocalDateTime startOfDay = dto.getDiveStartDate().atStartOfDay();
                LocalDateTime endOfDay = dto.getDiveStartDate().plusDays(1).atStartOfDay();

                Predicate rovDivePredicate = cb.between(
                        rovDiveJoin.get("diveStartDatetime"),
                        startOfDay,
                        endOfDay
                );
                predicates.add(rovDivePredicate);
            }

            if (dto.getDiveEndDate() != null) {
                LocalDateTime startOfDay = dto.getDiveEndDate().atStartOfDay();
                LocalDateTime endOfDay = dto.getDiveEndDate().plusDays(1).atStartOfDay();

                Predicate rovDivePredicate = cb.between(
                        rovDiveJoin.get("diveEndDatetime"),
                        startOfDay,
                        endOfDay
                );
                predicates.add(rovDivePredicate);
            }

            if (dto.getKeyword() != null) {
                Predicate keywordPredicate = cb.or(
                        cb.like(root.get("purpose"), "%" + dto.getKeyword() + "%"),
                        cb.like(root.get("equipmentDescription"), "%" + dto.getKeyword() + "%"),
                        cb.like(root.get("participants"), "%" + dto.getKeyword() + "%"),
                        cb.like(root.get("regionDescription"), "%" + dto.getKeyword() + "%"),
                        cb.like(root.get("plannedTrackDescription"), "%" + dto.getKeyword() + "%"),
                        cb.like(root.get("accomplishments"), "%" + dto.getKeyword() + "%"),
                        cb.like(root.get("operatorComments"), "%" + dto.getKeyword() + "%"),
                        cb.like(root.get("otherComments"), "%" + dto.getKeyword() + "%"),
                        cb.like(rovDiveJoin.get("briefAccomplishments"), "%" + dto.getKeyword() + "%")
                );
                predicates.add(keywordPredicate);
            }

            query.distinct(true);// Eliminate duplicate.
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
