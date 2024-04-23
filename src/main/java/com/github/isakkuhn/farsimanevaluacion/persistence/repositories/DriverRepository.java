package com.github.isakkuhn.farsimanevaluacion.persistence.repositories;

import com.github.isakkuhn.farsimanevaluacion.persistence.entities.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface DriverRepository extends JpaRepository<DriverEntity, UUID> {
}
