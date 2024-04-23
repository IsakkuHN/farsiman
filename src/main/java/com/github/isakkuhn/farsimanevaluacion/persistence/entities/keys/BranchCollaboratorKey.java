package com.github.isakkuhn.farsimanevaluacion.persistence.entities.keys;

import jakarta.persistence.Column;
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
public class BranchCollaboratorKey implements Serializable {

    @Column(unique = true, nullable = false, name = "branch_id")
    private Long branchId;

    @Column(unique = true, nullable = false, name = "collaborator_id")
    private UUID collaboratorId;

    @Override
    public int hashCode() {
        return Objects.hash(branchId, collaboratorId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BranchCollaboratorKey other = (BranchCollaboratorKey) obj;
        return Objects.equals(branchId, other.branchId) && Objects.equals(collaboratorId, other.collaboratorId);
    }
}
