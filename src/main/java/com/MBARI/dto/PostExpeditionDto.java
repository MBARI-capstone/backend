package com.MBARI.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostExpeditionDto {

    private LocalDateTime actualStartTime;
    private LocalDateTime actualEndTime;
    private String accomplishments;
    private String scientistComments;
    private String sciObjectiveMet;
    private String operatorComments;
    private Integer allEquipmentFunctioned;
    private String otherComments;
    private Integer updatedBy;


}
