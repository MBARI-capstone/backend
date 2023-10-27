package com.MBARI.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

@Data
public class PreExpeditionDto {
    //Ship Data
    //private Integer shipId;
    private String shipName;

    //User Data
    //private Integer userId;
    private String username;

    //Date Time Date
    //private int startMin;
    //private int startHour;
    private int startDay;
    private int startMonth;
    private int startYear;

    //private int endMin;
    //private int endHour;
    private int endDay;
    private int endMonth;
    private int endYear;
    //Expedition/Scheduling Data
    private Integer expeditionId;
    private String purpose;

    private Integer chiefScientistId;
    private String chiefScientist;

    private Integer principalInvestigatorId;
    private String principalInvestigator;

    private LocalDate scheduledStartTime;
    private LocalDate scheduledEndTime;

    private String equipmentDesc;
    private String participants;
    private String regionDesc;
    private String plannedTrackDesc;
}
