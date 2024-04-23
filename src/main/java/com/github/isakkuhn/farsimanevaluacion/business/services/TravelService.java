package com.github.isakkuhn.farsimanevaluacion.business.services;


import com.github.isakkuhn.farsimanevaluacion.persistence.entities.TravelDetailEntity;
import com.github.isakkuhn.farsimanevaluacion.persistence.entities.TravelEntity;
import com.github.isakkuhn.farsimanevaluacion.persistence.repositories.CollaboratorRepository;
import com.github.isakkuhn.farsimanevaluacion.persistence.repositories.TravelDetailRepository;
import com.github.isakkuhn.farsimanevaluacion.persistence.repositories.TravelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    private final TravelDetailRepository travelDetailRepository;

    private final CollaboratorRepository collaboratorRepository;

    private final AssignationService assignationService;

    public List<TravelEntity> getAllTravels() {
        return this.travelRepository.findAll();
    }

    public Optional<TravelEntity> getTravelById(Long id) {
       return this.travelRepository.findById(id);
    }

    public Optional<TravelEntity> createTravel(TravelEntity travel) {
        if(travel.getId() == null && !travel.isPaid()){
            return Optional.of(this.travelRepository.save(travel));
        }
        return Optional.empty();

    }

    public Optional<TravelEntity> payTravel(Long travelId) {
        TravelEntity travel = this.travelRepository.findById(travelId).orElse(null);
        if(travel != null){
            travel.setPaid(true);
            return Optional.of(this.travelRepository.save(travel));
        }
        return Optional.empty();
    }

    public List<TravelEntity> getAllTravelsByDriverIdBetween(UUID driverId, Date startDate, Date endDate) {
        return this.travelRepository.findAllByTravelDateBetweenAndDriverId(startDate, endDate, driverId);
    }

    public TravelDetailEntity addCollaboratorToTravel(TravelDetailEntity detail) {
        if(detail.getKey()==null){
            return null;
        }

        Optional<TravelEntity> travel = this.travelRepository.findById(detail.getKey().getTravelId());
        if(travel.isEmpty()){
            return null;
        }
        if(travel.get().getTravelDistance()>100){
            this.travelDetailRepository.deleteById(detail.getKey());
            return null;
        }
        Float travelerDistance = this.assignationService.getDistance(detail.getKey().getCollaboratorId(), travel.get().getBranch().getId());

        if(travelerDistance == null){
            return null;
        }

        travel.get().setTravelDistance(travel.get().getTravelDistance()+travelerDistance);
        this.travelRepository.save(travel.get());
        return this.travelDetailRepository.save(detail);


    }

    public List<TravelDetailEntity> getAllTravelersByTravelId(Long travelId) {
        return this.travelDetailRepository.findAllByTravel_Id(travelId);
    }



}
