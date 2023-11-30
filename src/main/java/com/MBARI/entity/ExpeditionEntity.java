package com.MBARI.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@Table(name="Expeditions")
public class ExpeditionEntity {

    // TODO: we need status like APPROVE, PENDING, COMPLETED

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expeditionId")
    private Integer expeditionId;

    @ManyToOne()
    @JoinColumn(name = "shipId")
    private ShipEntity ship;

    @ManyToOne()
    @JoinColumn(name = "chiefScientistId")
    private UserEntity chiefScientist;

    @ManyToOne()
    @JoinColumn(name = "principalInvestigatorId")
    private UserEntity principalInvestigator;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "scheduledStartDate")
    private LocalDate scheduledStartDate;

    @Column(name = "scheduledEndDate")
    private LocalDate scheduledEndDate;

    @Column(name = "equipmentDescription")
    private String equipmentDescription;

    @Column(name = "participants")
    private String participants;

    @Column(name = "regionDescription")
    private String regionDescription;

    @Column(name = "plannedTrackDescription")
    private String plannedTrackDescription;

    @Column(name = "isPreApproved")
    private Boolean isPreApproved;

    @Column(name = "actualStartDate")
    private LocalDate actualStartDate;

    @Column(name = "actualEndDate")
    private LocalDate actualEndDate;

    @Column(name = "accomplishments")
    private String accomplishments;

    @Column(name = "scientistComments")
    private String scientistComments;

    @Column(name = "sciObjectivesMet")
    private Boolean sciObjectivesMet;

    @Column(name = "operatorComments")
    private String operatorComments;

    @Column(name = "allEquipmentFunctioned")
    private Boolean allEquipmentFunctioned;

    @Column(name = "otherComments")
    private String otherComments;

    @ManyToOne()
    @JoinColumn(name = "updatedBy")
    private UserEntity updatedBy;

    @OneToMany(mappedBy = "relatedExpedition", fetch = FetchType.LAZY)
    private List<RovDiveEntity> rovDives;

}