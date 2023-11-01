package com.MBARI.dto;

import lombok.Data;
import java.time.LocalDate;
@Data
public class SearchDto {
    private Integer shipId;
    private Integer chiefScientistId;
    private Integer principalInvestigatorId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer sequenceNumber;
    private String diveNumber;
//    private String status;
    private String keyword;
}
