package com.MBARI.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RovDiveDto {
    private Integer expeditionId;
    private String rovName;
    private String diveNumber;
    private LocalDateTime diveStartDatetime;
    private LocalDateTime diveEndDatetime;
    private Integer diveChiefScientistId;
    private String briefAccomplishments;
}
