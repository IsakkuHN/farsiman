package com.github.isakkuhn.farsimanevaluacion.business.services;

import com.github.isakkuhn.farsimanevaluacion.persistence.entities.BranchCollaboratorAssignationEntity;
import com.github.isakkuhn.farsimanevaluacion.persistence.repositories.AssignationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AssignationService {

    private AssignationRepository assignationRepository;

    public List<BranchCollaboratorAssignationEntity> getAssignations() {
        return this.assignationRepository.findAll();
    }


    public BranchCollaboratorAssignationEntity saveAssignation(BranchCollaboratorAssignationEntity assignation) {
        if(assignation.getKey() != null){
            return this.assignationRepository.saveAndFlush(assignation);
        }
        return null;
    }

    public List<BranchCollaboratorAssignationEntity> getAssignationsByBranchId(Long branchId) {
        return this.assignationRepository.findAllByBranch_Id(branchId);
    }

    public List<BranchCollaboratorAssignationEntity> getAssignationsByCollaboratorId(UUID collaboratorId) {
        return this.assignationRepository.findAllByCollaborator_Id(collaboratorId);
    }

    public Float getTotalDistance(List<UUID> collaboratorIds, Long branchId) {
        Float totalDistance = 0f;
        List<BranchCollaboratorAssignationEntity> distances = this.assignationRepository.findAllByCollaboratorIdInAndBranch_Id(collaboratorIds, branchId);
        if(distances == null || distances.isEmpty()) {
            return 0f;
        }

        for(BranchCollaboratorAssignationEntity distance : distances) {
            totalDistance += distance.getDistance();
        }
        return totalDistance;

    }

    public Float getDistance(UUID collaboratorId, Long branchId) {
        return this.assignationRepository.finDistanceByBranchIdAndCollaboratorId(branchId, collaboratorId);
    }
}
