package com.github.isakkuhn.farsimanevaluacion.business.services;

import com.github.isakkuhn.farsimanevaluacion.persistence.entities.DriverEntity;
import com.github.isakkuhn.farsimanevaluacion.persistence.repositories.DriverRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public List<DriverEntity> findAllDrivers() {
        return driverRepository.findAll();
    }

    public DriverEntity findDriverById(UUID id) {
        return driverRepository.findById(id).orElse(null);
    }

    public DriverEntity createDriver(DriverEntity driver) {
        if (driver.getId() == null) {
            return driverRepository.save(driver);
        }
        return null;
    }

    public DriverEntity updateDriver(DriverEntity driver) {
        boolean exists = driverRepository.existsById(driver.getId());
        if (exists) {
            return driverRepository.save(driver);
        }
        return null;
    }

    public boolean deleteDriver(UUID id) {
        boolean exists = driverRepository.existsById(id);
        if (exists) {
            driverRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
