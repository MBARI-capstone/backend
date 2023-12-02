package com.MBARI.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpeditionDto {
    private Integer expeditionId;
    private String shipName;
    private String chiefScientistName;
    private String principalInvestigatorName;
    private String purpose;
    private LocalDate scheduledStartDate;
    private LocalDate scheduledEndDate;
    private String equipmentDescription;
    private String participants;
    private String regionDescription;
    private String plannedTrackDescription;
//    private Boolean isPreApproved;
    private LocalDate actualStartDate;
    private LocalDate actualEndDate;
    private String accomplishments;
    private String scientistComments;
    private Boolean sciObjectivesMet;
    private String operatorComments;
    private Boolean allEquipmentFunctioned;
    private String otherComments;
    private Integer updatedBy;

}
