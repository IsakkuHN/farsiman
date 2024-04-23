package com.github.isakkuhn.farsimanevaluacion.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "El nombre no puede estar en blanco.")
    @NotNull(message = "El nombre es un campo requerido.")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "El apellido no puede estar en blanco.")
    @NotNull(message = "El apellido es un campo requerido.")
    @Column(nullable = false)
    private String lastName;
}
