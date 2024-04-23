package com.github.isakkuhn.farsimanevaluacion.presentation.controllers;

import com.github.isakkuhn.farsimanevaluacion.business.services.AssignationService;
import com.github.isakkuhn.farsimanevaluacion.persistence.entities.BranchCollaboratorAssignationEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/assignation")
@AllArgsConstructor
public class AssignationController {

    private final AssignationService assignationService;

    @GetMapping
    public ResponseEntity<List<BranchCollaboratorAssignationEntity>> getAssignations() {
        return ResponseEntity.ok(this.assignationService.getAssignations());
    }

    @PostMapping
    public ResponseEntity<BranchCollaboratorAssignationEntity> createAssignation(@RequestBody BranchCollaboratorAssignationEntity assignation) {
        BranchCollaboratorAssignationEntity entity = this.assignationService.saveAssignation(assignation);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(entity);
    }

    @GetMapping("branch/{branchId}")
    public ResponseEntity<List<BranchCollaboratorAssignationEntity>> getAssignationByBranchId(@PathVariable Long branchId) {
        return ResponseEntity.ok(this.assignationService.getAssignationsByBranchId(branchId));
    }

    @GetMapping("collaborator/{collaboratorId}")
    public ResponseEntity<List<BranchCollaboratorAssignationEntity>> getAssignationByCollaboratorId(@PathVariable UUID collaboratorId) {
        return ResponseEntity.ok(this.assignationService.getAssignationsByCollaboratorId(collaboratorId));
    }

    @GetMapping("total_distance/{branchId}")
    public ResponseEntity<Float> getTotalDistance(@RequestBody List<UUID> collaboratorIds, @PathVariable Long branchId) {
        return ResponseEntity.ok(this.assignationService.getTotalDistance(collaboratorIds, branchId));
    }
}
