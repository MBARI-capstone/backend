package com.MBARI.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Data
@Entity
@NoArgsConstructor
@Table(name="Expeditions")
public class ExpeditionEntity {

    //TODO Fix ID's of foreign keys

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expeditionId")
    private Integer expeditionId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipId")
    private Integer shipId;

    @Column(name = "purpose",nullable = false)
    private String purpose;

    @Column(name = "chiefScientistId",nullable = false)
    private Integer chiefScientist;

    @Column(name = "principalInvestigator", nullable= false)
    private Integer principalInvestigator;

    @Column(name = "scheduledStartDatetime",nullable = false)
    private Calendar scheduledStartDatetime;

    @Column(name = "scheduledEndDatetime",nullable = false)
    private Calendar scheduledEndDatetime;

    @Column(name = "equipmentDescription",nullable = false)
    private String equipmentDescription;

    @Column(name = "participants",nullable = false)
    private String participants;

    @Column(name = "regionDescription",nullable = false)
    private String regionDescription;

    @Column(name = "plannedTrackDescription",nullable = false)
    private String plannedTrackDescription;

    @Column(name = "actualStartDatetime",nullable = false)
    private Calendar  actualStartDatetime;

    @Column(name = "actualEndDatetime",nullable = false)
    private Calendar  actualEndDatetime;

    @Column(name = "accomplishments",nullable = false)
    private String accomplishments;

    @Column(name = "scientistComments",nullable = true)
    private String scientistComments;

    @Column(name = "sciObjectivesMet",nullable = false)
    private Boolean sciObjectivesMet;

    @Column(name = "operatorComments",nullable = true)
    private Boolean operatorComments;

    @Column(name = "allEquipmentFunctioned",nullable = false)
    private Boolean allEquipmentFunctioned;

    @Column(name = "otherComments",nullable = true)
    private String otherComments;

    @Column(name = "updatedBy",nullable = false)
    private Integer updatedBy;
}
