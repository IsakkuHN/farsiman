package com.github.isakkuhn.farsimanevaluacion.persistence.repositories;

import com.github.isakkuhn.farsimanevaluacion.persistence.entities.TravelDetailEntity;
import com.github.isakkuhn.farsimanevaluacion.persistence.entities.keys.TravelCollaboratorKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelDetailRepository extends JpaRepository<TravelDetailEntity, TravelCollaboratorKey> {
    List<TravelDetailEntity> findAllByTravel_Id(Long id);
}
