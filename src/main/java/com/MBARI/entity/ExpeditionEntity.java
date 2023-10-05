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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chiefScientistId")
    private Integer chiefScientistId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "principalInvestigatorId")
    private Integer principalInvestigator;

    @Column(name = "scheduledStartDatetime",nullable = false)
    private Calendar scheduledStartDatetime;

    @Column(name = "scheduledEndDatetime",nullable = false)
    private Calendar scheduledEndDatetime;

    @Column(name = "equipmentDescription",nullable = false)
    private String equipmentDescription;

    @Column(name = "participants",nullable = false)
    private String participants;


    //TODO
    //                             participants TEXT,
    //                             regionDescription VARCHAR(2048),
    //                             plannedTrackDescription VARCHAR(6144),
    //                             actualStartDatetime DATETIME,
    //                             actualEndDatetime DATETIME,
    //                             accomplishments TEXT,
    //                             scientistComments TEXT,
    //                             sciObjectivesMet BOOLEAN,
    //                             operatorComments TEXT,
    //                             allEquipmentFunctioned BOOLEAN,
    //                             otherComments TEXT,
    //                             updatedBy INT,
}
