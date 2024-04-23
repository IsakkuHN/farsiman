package com.github.isakkuhn.farsimanevaluacion.presentation.dto;


import java.io.Serializable;
import java.util.UUID;

public record TravelerDto(Long travelId, UUID travelerId) implements Serializable {

}
