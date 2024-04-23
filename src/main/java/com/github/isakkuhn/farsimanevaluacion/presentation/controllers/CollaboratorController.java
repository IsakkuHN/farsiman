package com.github.isakkuhn.farsimanevaluacion.presentation.controllers;

import com.github.isakkuhn.farsimanevaluacion.business.services.CollaboratorService;
import com.github.isakkuhn.farsimanevaluacion.persistence.entities.CollaboratorEntity;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/collaborator")
@AllArgsConstructor
public class CollaboratorController {

    private final CollaboratorService collaboratorService;

    @GetMapping
    public ResponseEntity<List<CollaboratorEntity>> getAllCollaborators() {
        return ResponseEntity.ok().body(this.collaboratorService.getCollaborators());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollaboratorEntity> getCollaboratorById(@PathVariable("id") UUID id) {
        CollaboratorEntity collaborator = this.collaboratorService.getCollaboratorById(id);
        if (collaborator == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(collaborator);
    }

    @PostMapping
    public ResponseEntity<CollaboratorEntity> addCollaborator(@RequestBody @Valid CollaboratorEntity collaborator) {
        return ResponseEntity.ok().body(this.collaboratorService.saveCollaborator(collaborator));
    }

    @PutMapping
    public ResponseEntity<CollaboratorEntity> updateCollaborator(@RequestBody @Valid CollaboratorEntity collaborator) {
        CollaboratorEntity entity = this.collaboratorService.updateCollaborator(collaborator);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStatus> deleteCollaborator(@PathVariable("id") UUID id) {
        boolean deleted = this.collaboratorService.deleteCollaborator(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }
}
