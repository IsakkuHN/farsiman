package com.github.isakkuhn.farsimanevaluacion.persistence.repositories;

import com.github.isakkuhn.farsimanevaluacion.persistence.entities.TravelDetailEntity;
import com.github.isakkuhn.farsimanevaluacion.persistence.entities.keys.TravelCollaboratorKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TravelDetailRepository extends JpaRepository<TravelDetailEntity, TravelCollaboratorKey> {
    List<TravelDetailEntity> findAllByTravel_Id(Long id);
    Optional<TravelDetailEntity> findByTravel_TravelDateAndCollaborator_Id(Date travelDate, UUID collaboratorId);
}
