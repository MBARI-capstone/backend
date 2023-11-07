package com.MBARI.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResponseRovDiveDto {
    private String rovName;
    private String diveNumber;
    private LocalDateTime diveStartDatetime;
    private LocalDateTime diveEndDatetime;
    private String diveChiefScientistName;
    private String briefAccomplishments;
}
