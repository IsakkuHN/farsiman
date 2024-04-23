package com.github.isakkuhn.farsimanevaluacion.persistence.entities.keys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelCollaboratorKey implements Serializable {

    private UUID collaboratorId;
    private Long travelId;

    @Override
    public int hashCode() {
        return Objects.hash(collaboratorId, travelId);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        TravelCollaboratorKey that = (TravelCollaboratorKey) obj;
        return Objects.equals(travelId, that.travelId) && Objects.equals(collaboratorId, that.collaboratorId);
    }
}
