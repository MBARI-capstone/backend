package com.MBARI.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Calendar;

@Data
public class PreExpeditionDto {
    private Integer expeditionId;
    private Integer ShipId;
    private String purpose;
    private Integer chiefScientistId;
    private Integer principalInvestigator;
    private LocalDateTime scheduledStartTime;
    private LocalDateTime scheduledEndTime;
    private String equipmentDesc;
    private String participants;
    private String regionDesc;
    private String plannedTrackDesc;
}
