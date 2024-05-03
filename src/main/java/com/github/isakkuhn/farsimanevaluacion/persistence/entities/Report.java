package com.github.isakkuhn.farsimanevaluacion.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Date creationReportDate = new Date(System.currentTimeMillis());

    private Date fromDate;
    private Date toDate;
    private BigDecimal totalAmountPaid;

    @OneToMany
    List<TravelEntity> travels;

    @OneToOne
    DriverEntity driver;


}
