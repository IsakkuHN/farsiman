package com.github.isakkuhn.farsimanevaluacion.business.services;

import com.github.isakkuhn.farsimanevaluacion.persistence.entities.CollaboratorEntity;
import com.github.isakkuhn.farsimanevaluacion.persistence.repositories.CollaboratorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CollaboratorService {

    private final CollaboratorRepository collaboratorRepository;

    public CollaboratorService(CollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }

    public List<CollaboratorEntity> getCollaborators() {
        return this.collaboratorRepository.findAll();
    }

    public  CollaboratorEntity getCollaboratorById(UUID id) {
        return this.collaboratorRepository.findById(id).orElse(null);
    }

    public CollaboratorEntity saveCollaborator(CollaboratorEntity collaborator) {
        if (collaborator.getId() == null) {
            return this.collaboratorRepository.save(collaborator);
        }
        return null;
    }

    public CollaboratorEntity updateCollaborator(CollaboratorEntity collaborator) {
        CollaboratorEntity oldCollaborator = this.getCollaboratorById(collaborator.getId());
        if (oldCollaborator != null) {
            return this.collaboratorRepository.save(collaborator);
        }
        return null;
    }

    public boolean deleteCollaborator(UUID id) {
        CollaboratorEntity collaborator = this.getCollaboratorById(id);
        if (collaborator != null) {
            this.collaboratorRepository.delete(collaborator);
            return true;
        }
        return false;
    }
}
