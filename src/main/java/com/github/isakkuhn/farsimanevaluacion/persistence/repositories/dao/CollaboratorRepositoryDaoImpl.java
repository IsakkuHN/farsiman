package com.github.isakkuhn.farsimanevaluacion.persistence.repositories.dao;


import com.github.isakkuhn.farsimanevaluacion.persistence.entities.CollaboratorEntity;
import com.github.isakkuhn.farsimanevaluacion.persistence.repositories.CollaboratorRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CollaboratorRepositoryDaoImpl {

    private final CollaboratorRepository repo;

    public CollaboratorRepositoryDaoImpl(CollaboratorRepository repo) {
        this.repo = repo;
    }

    public CollaboratorEntity saveCollaborator(CollaboratorEntity collaborator){
        if(collaborator.getId() == null){
            return repo.save(collaborator);
        }
        return null;
    }

}