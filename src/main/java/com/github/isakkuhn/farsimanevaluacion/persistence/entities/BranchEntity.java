package com.github.isakkuhn.farsimanevaluacion.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BranchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El nombre de la sucursal no puede estar en blanco.")
    @NotNull(message = "El nombre de la sucursal es un campo requerido.")
    private String name;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "La direccion de la sucursal no puede estar en blanco.")
    @NotNull(message = "La direccion de la sucursal es un campo requerido.")
    private String address;
}
