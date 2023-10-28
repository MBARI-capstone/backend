package com.MBARI.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PostExpeditionDto {

    private int startMin;
    private int startHour;
    private int startDay;
    private int startMonth;
    private int startYear;

    private int endMin;
    private int endHour;
    private int endDay;
    private int endMonth;
    private int endYear;

    private LocalDate actualStartTime;
    private LocalDate actualEndTime;

    private String accomplishments;
    private String scientistComments;
    private String sciObjectiveMet;
    private String operatorComments;
    private Integer allEquipmentFunctioned;
    private String otherComments;
    private Integer updatedBy;


}
