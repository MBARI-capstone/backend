package com.MBARI.dto;

import lombok.Data;

import java.util.Calendar;
@Data
public class ExpeditionDto {
    private String ShipName;
    private String Purpose;
    private String ChiefScientist;
    private String PrincipalInvestigator;
    private String Participants;
    private String EquipmentDesc;
    private String RegionDesc;
    private String PlannedTrackDesc;
    private String Description;

    private Calendar StartDatetime;
    private Calendar EndDatetime;

    Boolean Approved;
}