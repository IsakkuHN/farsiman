package com.github.isakkuhn.farsimanevaluacion.persistence.repositories;

import com.github.isakkuhn.farsimanevaluacion.persistence.entities.BranchCollaboratorAssignationEntity;
import com.github.isakkuhn.farsimanevaluacion.persistence.entities.keys.BranchCollaboratorKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AssignationRepository extends JpaRepository<BranchCollaboratorAssignationEntity, BranchCollaboratorKey> {

    List<BranchCollaboratorAssignationEntity> findAllByBranch_Id(Long branchId);
    List<BranchCollaboratorAssignationEntity> findAllByCollaborator_Id(UUID collaboratorId);
    List<BranchCollaboratorAssignationEntity> findAllByCollaboratorIdInAndBranch_Id(List<UUID> collaboratorIds, Long branchId);

    // Query Methods

    @Query("SELECT a.distance FROM BranchCollaboratorAssignationEntity a WHERE a.key.branchId=?1 AND a.key.collaboratorId =?2")
    Float finDistanceByBranchIdAndCollaboratorId(Long branchId, UUID collaboratorId);
}
