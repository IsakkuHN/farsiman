package com.github.isakkuhn.farsimanevaluacion.presentation.controllers;

import com.github.isakkuhn.farsimanevaluacion.business.services.TravelService;
import com.github.isakkuhn.farsimanevaluacion.persistence.entities.TravelDetailEntity;
import com.github.isakkuhn.farsimanevaluacion.persistence.entities.TravelEntity;
import com.github.isakkuhn.farsimanevaluacion.presentation.dto.TravelReportDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/travel")
@AllArgsConstructor
public class TravelController {

    private final TravelService travelService;

    @GetMapping
    public ResponseEntity<List<TravelEntity>> getAllTravels() {
        return ResponseEntity.ok(this.travelService.getAllTravels());
    }

    @PostMapping
    public ResponseEntity<TravelEntity> addTravel(@RequestBody TravelEntity travel) {
        Optional<TravelEntity> entity = this.travelService.createTravel(travel);
        return entity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/report")
    public ResponseEntity<List<TravelEntity>> getTravelReport(@RequestBody TravelReportDto requestDto) {
        List<TravelEntity> travelsByDriver = this.travelService.getAllTravelsByDriverIdBetween(
                requestDto.getDriverId(), requestDto.getStartDate(), requestDto.getEndDate());
        return ResponseEntity.ok(travelsByDriver);
    }

    @PutMapping("pay/{travelId}")
    public ResponseEntity<TravelEntity> payTravel(@PathVariable Long travelId){
        Optional<TravelEntity> entity = this.travelService.payTravel(travelId);
        return entity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/traveler")
    public ResponseEntity<TravelDetailEntity> addTraveler(@RequestBody TravelDetailEntity traveler){
        TravelDetailEntity travelerRetrieved = this.travelService.addCollaboratorToTravel(traveler);
        if(travelerRetrieved == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(travelerRetrieved);
    }

    @GetMapping("/travelers/{travelId}")
    public ResponseEntity<List<TravelDetailEntity>> getAllTravelers(@PathVariable Long travelId) {
        List<TravelDetailEntity> travelers = this.travelService.getAllTravelersByTravelId(travelId);
        return ResponseEntity.ok(travelers);
    }
}
