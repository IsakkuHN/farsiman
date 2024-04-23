package com.github.isakkuhn.farsimanevaluacion.persistence.repositories;

import com.github.isakkuhn.farsimanevaluacion.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
