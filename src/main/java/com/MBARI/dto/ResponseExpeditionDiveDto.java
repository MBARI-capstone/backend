package com.MBARI.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class ResponseExpeditionDiveDto {
    private Integer expeditionId;
    private String shipName;
    private String expeditionChiefScientistName;
    private String principalInvestigatorName;
    private String purpose;
    private LocalDate scheduledStartDate;
    private LocalDate scheduledEndDate;
    private String equipmentDescription;
    private String participants;
    private String regionDescription;
    private String plannedTrackDescription;
    private LocalDate actualStartDate;
    private LocalDate actualEndDate;
    private String accomplishments;
    private String scientistComments;
    private Boolean sciObjectivesMet;
    private String operatorComments;
    private Boolean allEquipmentFunctioned;
    private String otherComments;
    private String updatedByUserName;

    private List<ResponseRovDiveDto> rovDives;
}