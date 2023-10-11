package com.MBARI.dto;

import lombok.Data;

import java.util.Calendar;
@Data
public class ExpeditionDto {
    //used to transfer specific combinations of data
    //between database and API


    public ExpeditionDto() {
        super();
    }

    //TODO Not sure what other means of Data Transfer we need
    public ExpeditionDto(String shipName, String purpose, String chiefScientist, String principalInvestigator, String participants, String equipmentDesc, String regionDesc, String plannedTrackDesc, String description, Calendar startDatetime, Calendar endDatetime) {
        ShipName = shipName;
        Purpose = purpose;
        ChiefScientist = chiefScientist;
        PrincipalInvestigator = principalInvestigator;
        Participants = participants;
        EquipmentDesc = equipmentDesc;
        RegionDesc = regionDesc;
        PlannedTrackDesc = plannedTrackDesc;
        Description = description;
        StartDatetime = startDatetime;
        EndDatetime = endDatetime;
    }

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