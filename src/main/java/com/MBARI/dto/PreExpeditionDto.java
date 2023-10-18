package com.MBARI.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Calendar;

@Data
public class PreExpeditionDto {
    //Ship Data
    private Integer shipId;
    private String shipName;


    //User Data
    private Integer userId;
    private String username;

    //Expedition/Scheduling Data
    private Integer expeditionId;
    private String purpose;

    private Integer chiefScientistId;
    private String chiefScientist;

    private Integer principalInvestigatorId;
    private String principalInvestigator;

    private LocalDateTime scheduledStartTime;
    private LocalDateTime scheduledEndTime;

    private String equipmentDesc;
    private String participants;
    private String regionDesc;
    private String plannedTrackDesc;
}
