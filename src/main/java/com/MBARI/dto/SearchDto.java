package com.MBARI.dto;

import lombok.Data;
import java.time.LocalDate;
@Data
public class SearchDto {
    private Integer shipId;
    private Integer expeditionChiefScientistId;
    private Integer principalInvestigatorId;
    private LocalDate expeditionStartDate;
    private LocalDate expeditionEndDate;
    private Integer expeditionSequenceNumber;
    private Boolean sciObjectivesMet;
    private Boolean allEquipmentFunctioned;

    private String diveNumber;
    private Integer diveChiefScientistId;
    private LocalDate diveStartDate;
    private LocalDate diveEndDate;

    private String keyword;
}
