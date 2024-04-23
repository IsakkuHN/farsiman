package com.github.isakkuhn.farsimanevaluacion.persistence.entities;

import com.github.isakkuhn.farsimanevaluacion.persistence.entities.keys.BranchCollaboratorKey;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Entity
@Getter
@Setter
public class BranchCollaboratorAssignationEntity {

    @EmbeddedId
    private BranchCollaboratorKey key;

    @NotNull
    @Range(min = 1, max = 50, message = "La distancia de una sucursal a la casa del colaborador no debe ser menor o igual a 0 km y menor a 50 km")
    @Column(nullable = false, updatable = false)
    private Float distance;

    // Relations

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id", insertable = false, updatable = false)
    private BranchEntity branch;

    @ManyToOne
    @JoinColumn(name = "collaborator_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CollaboratorEntity collaborator;
}
