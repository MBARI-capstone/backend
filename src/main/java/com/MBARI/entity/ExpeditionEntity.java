package com.MBARI.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;


@Data
@Entity
@NoArgsConstructor
@Table(name="Expeditions")
public class ExpeditionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expeditionId")
    private Integer expeditionId;

    @ManyToOne()
    @JoinColumn(name = "shipId")
    private Ship shipId;

    @Column(name = "purpose", nullable = false)
    private String purpose;

    //TODO Person Entity
    @ManyToOne()
    @JoinColumn(name = "chiefScientistId")
    private UserEntity chiefScientist;

    @ManyToOne()
    @JoinColumn(name = "principalInvestigator")
    private UserEntity principalInvestigator;

    @Column(name = "scheduledStartDatetime", nullable = false)
    private Calendar scheduledStartDatetime;

    @Column(name = "scheduledEndDatetime", nullable = false)
    private Calendar scheduledEndDatetime;

    @Column(name = "equipmentDescription", nullable = false)
    private String equipmentDescription;

    @Column(name = "participants", nullable = false)
    private String participants;

    @Column(name = "regionDescription", nullable = false)
    private String regionDescription;

    @Column(name = "plannedTrackDescription", nullable = false)
    private String plannedTrackDescription;

    @Column(name = "actualStartDatetime", nullable = false)
    private LocalDateTime actualStartDatetime;

    @Column(name = "actualEndDatetime", nullable = false)
    private LocalDateTime actualEndDatetime;

    @Column(name = "accomplishments", nullable = false)
    private String accomplishments;

    @Column(name = "scientistComments", nullable = true)
    private String scientistComments;

    @Column(name = "sciObjectivesMet", nullable = false)
    private Integer sciObjectivesMet;

    @Column(name = "operatorComments", nullable = true)
    private String operatorComments;

    @Column(name = "allEquipmentFunctioned", nullable = false)
    private Integer allEquipmentFunctioned;

    @Column(name = "otherComments", nullable = true)
    private String otherComments;

    @ManyToOne()
    @JoinColumn(name = "updatedBy")
    private UserEntity updatedBy;
}