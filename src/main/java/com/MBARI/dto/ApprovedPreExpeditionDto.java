package com.MBARI.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ApprovedPreExpeditionDto {
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
}
