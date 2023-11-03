package com.MBARI.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PostExpeditionDto {

    private LocalDate actualStartTime;
    private LocalDate actualEndTime;

    private String accomplishments;
    private String scientistComments;
    private Integer sciObjectiveMet;
    private String operatorComments;
    private Integer allEquipmentFunctioned;
    private String otherComments;
    private Integer updatedBy;


}
