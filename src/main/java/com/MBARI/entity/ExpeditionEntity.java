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

    //TODO verify that we need the fields to be in this way.

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

    public Integer getExpeditionId() {
        return expeditionId;
    }

//    public void setExpeditionId(Integer expeditionId) {
//        this.expeditionId = expeditionId;
//    }

    public Integer getShipId() {
        return shipId;
    }

    public void setShipId(Integer shipId) {
        this.shipId = shipId;
    }
    public String getPurpose() {
        return purpose;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    public Integer getChiefScientist() {
        return chiefScientist;
    }
    public void setChiefScientist(Integer chiefScientist) {
        this.chiefScientist = chiefScientist;
    }
    public Integer getPrincipalInvestigator() {
        return principalInvestigator;
    }
    public void setPrincipalInvestigator(Integer principalInvestigator) {
        this.principalInvestigator = principalInvestigator;
    }

    public Calendar getScheduledStartDatetime() {
        return scheduledStartDatetime;
    }

    public void setScheduledStartDatetime(Calendar scheduledStartDatetime) {
        this.scheduledStartDatetime = scheduledStartDatetime;
    }

    public Calendar getScheduledEndDatetime() {
        return scheduledEndDatetime;
    }

    public void setScheduledEndDatetime(Calendar scheduledEndDatetime) {
        this.scheduledEndDatetime = scheduledEndDatetime;
    }

    public String getEquipmentDescription() {
        return equipmentDescription;
    }

    public void setEquipmentDescription(String equipmentDescription) {
        this.equipmentDescription = equipmentDescription;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }

    public String getPlannedTrackDescription() {
        return plannedTrackDescription;
    }

    public void setPlannedTrackDescription(String plannedTrackDescription) {
        this.plannedTrackDescription = plannedTrackDescription;
    }

    public Calendar getActualStartDatetime() {
        return actualStartDatetime;
    }

    public void setActualStartDatetime(Calendar actualStartDatetime) {
        this.actualStartDatetime = actualStartDatetime;
    }

    public Calendar getActualEndDatetime() {
        return actualEndDatetime;
    }

    public void setActualEndDatetime(Calendar actualEndDatetime) {
        this.actualEndDatetime = actualEndDatetime;
    }

    public String getAccomplishments() {
        return accomplishments;
    }

    public void setAccomplishments(String accomplishments) {
        this.accomplishments = accomplishments;
    }

    public String getScientistComments() {
        return scientistComments;
    }

    public void setScientistComments(String scientistComments) {
        this.scientistComments = scientistComments;
    }

    public Boolean getSciObjectivesMet() {
        return sciObjectivesMet;
    }

    public void setSciObjectivesMet(Boolean sciObjectivesMet) {
        this.sciObjectivesMet = sciObjectivesMet;
    }

    public Boolean getOperatorComments() {
        return operatorComments;
    }

    public void setOperatorComments(Boolean operatorComments) {
        this.operatorComments = operatorComments;
    }

    public Boolean getAllEquipmentFunctioned() {
        return allEquipmentFunctioned;
    }

    public void setAllEquipmentFunctioned(Boolean allEquipmentFunctioned) {
        this.allEquipmentFunctioned = allEquipmentFunctioned;
    }

    public String getOtherComments() {
        return otherComments;
    }

    public void setOtherComments(String otherComments) {
        this.otherComments = otherComments;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
}
