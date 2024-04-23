package com.github.isakkuhn.farsimanevaluacion.presentation.controllers;

import com.github.isakkuhn.farsimanevaluacion.business.services.DriverService;
import com.github.isakkuhn.farsimanevaluacion.persistence.entities.DriverEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/driver")
@AllArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<DriverEntity>> getAllDrivers() {
        return ResponseEntity.ok(this.driverService.findAllDrivers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverEntity> getDriverById(@PathVariable UUID id) {
        DriverEntity driver = this.driverService.findDriverById(id);
        if (driver == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(driver);
    }

    @PostMapping
    public ResponseEntity<DriverEntity> createDriver(@RequestBody DriverEntity driver) {
        if(driver.getId() == null) {
            return ResponseEntity.ok(this.driverService.createDriver(driver));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<DriverEntity> updateDriver(@RequestBody DriverEntity driver) {
        DriverEntity driverEntity = this.driverService.findDriverById(driver.getId());
        if (driverEntity != null) {
            return ResponseEntity.ok(this.driverService.updateDriver(driver));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDriver(@PathVariable UUID id) {
        boolean deleted = this.driverService.deleteDriver(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
