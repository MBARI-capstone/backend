package com.MBARI.entity;

import com.MBARI.dto.RovDiveDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name="RovDives")
public class RovDiveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer diveId;

    @Column(name = "rovName")
    private String rovName;

    @Column(name = "diveNumber")
    private String diveNumber;

    @Column(name = "diveStartDatetime")
    private LocalDateTime diveStartDatetime;

    @Column(name = "diveEndDatetime")
    private LocalDateTime diveEndDatetime;

    @ManyToOne()
    @JoinColumn(name = "diveChiefScientistId")
    private UserEntity diveChiefScientist;

    @Column(name = "briefAccomplishments", length = 2048)
    private String briefAccomplishments;

    @ManyToOne()
    @JoinColumn(name = "expeditionId")
    private ExpeditionEntity relatedExpedition;

    public RovDiveEntity(RovDiveDto rovDiveDto) {
        this.rovName = rovDiveDto.getRovName();
        this.diveNumber = rovDiveDto.getDiveNumber();
        this.diveStartDatetime = rovDiveDto.getDiveStartDatetime();
        this.diveEndDatetime = rovDiveDto.getDiveEndDatetime();
        this.briefAccomplishments = rovDiveDto.getBriefAccomplishments();
    }
}