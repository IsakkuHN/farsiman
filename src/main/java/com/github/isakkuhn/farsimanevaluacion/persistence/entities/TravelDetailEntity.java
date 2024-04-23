package com.github.isakkuhn.farsimanevaluacion.persistence.entities;

import com.github.isakkuhn.farsimanevaluacion.persistence.entities.keys.TravelCollaboratorKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TravelDetailEntity {

    @EmbeddedId
    private TravelCollaboratorKey key;

    // Relations
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "travelId", referencedColumnName = "id", insertable = false, updatable = false)
    private TravelEntity travel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "collaboratorId", referencedColumnName = "id", insertable = false, updatable = false)
    private CollaboratorEntity collaborator;
}
