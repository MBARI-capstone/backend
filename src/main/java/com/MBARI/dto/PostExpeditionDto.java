package com.MBARI.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PostExpeditionDto {
    private Integer expeditionId;
    private LocalDate actualStartDate;
    private LocalDate actualEndDate;
    private String accomplishments;
    private String scientistComments;
    private Boolean sciObjectivesMet;
    private String operatorComments;
    private Boolean allEquipmentFunctioned;
    private String otherComments;
    private Integer updatedBy;
}
