package com.github.isakkuhn.farsimanevaluacion.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Setter
@Getter
public class TravelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private Date travelDate;

    @Column(nullable = false)
    @NotNull
    private float travelDistance;

    @Column(nullable = false)
    @NotNull
    private BigDecimal travelRatePerKm;

    @Column(nullable = false)
    private boolean isPaid = false;

    // Relations

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private DriverEntity driver;

    @ManyToOne
    @JoinColumn(nullable = false)
    private BranchEntity branch;

}
