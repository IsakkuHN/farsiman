package com.github.isakkuhn.farsimanevaluacion.persistence.repositories;

import com.github.isakkuhn.farsimanevaluacion.persistence.entities.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<BranchEntity, Long> {
}
