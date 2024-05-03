package com.github.isakkuhn.farsimanevaluacion.presentation.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record TravelRequestDto(
        @NotBlank(message = "La fecha del viaje es obligatoria") String travelDate,
        @Positive(message = "La distancia del viaje debe de ser mayor a cero")
        @Max(message = "La distancia no debe de exceder los 50 km.", value = 100) Float travelDistance,
        @Positive(message = "El precio por km debe ser superior a cero.") BigDecimal travelRate,
        @NotBlank(message = "El id del conductor no debe de estar vacio") String driverId,
        @NotNull(message = "El id de la sucursal es obligatorio") Long branchId,
        @NotEmpty(message = "La lista de pasajeros no puede estar vacia") List<String> travelers) {
}

