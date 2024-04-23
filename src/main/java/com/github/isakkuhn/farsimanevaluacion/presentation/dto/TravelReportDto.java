package com.github.isakkuhn.farsimanevaluacion.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelReportDto implements Serializable {

    private Date startDate;
    private Date endDate;
    private UUID driverId;
}
