package com.github.isakkuhn.farsimanevaluacion.persistence.repositories;

import com.github.isakkuhn.farsimanevaluacion.persistence.entities.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface TravelRepository extends JpaRepository<TravelEntity, Long> {

    List<TravelEntity> findAllByTravelDateBetweenAndDriverId(Date from, Date to, UUID driverId);

}
