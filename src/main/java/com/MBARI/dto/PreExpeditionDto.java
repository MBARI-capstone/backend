package com.MBARI.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

@Data
public class PreExpeditionDto {
    private Integer shipId;
    private Integer chiefScientistId;
    private Integer principalInvestigatorId;
    private String purpose;
    private LocalDate scheduledStartDate;
    private LocalDate scheduledEndDate;
    private String equipmentDescription;
    private String participants;
    private String regionDescription;
    private String plannedTrackDescription;
}
