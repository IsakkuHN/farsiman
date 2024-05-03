package com.github.isakkuhn.farsimanevaluacion.business.services;


import com.github.isakkuhn.farsimanevaluacion.persistence.entities.*;
import com.github.isakkuhn.farsimanevaluacion.persistence.entities.keys.TravelCollaboratorKey;
import com.github.isakkuhn.farsimanevaluacion.persistence.repositories.*;
import com.github.isakkuhn.farsimanevaluacion.presentation.dto.TravelRequestDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final UserRepository userRepository;

    private final BranchRepository branchRepository;

    private final DriverRepository driverRepository;

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
        return this.travelRepository.findAllByTravelDateBetweenAndDriverIdAndIsPaidFalse(startDate, endDate, driverId);
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

    @Transactional
    public Optional<TravelEntity> saveTravel(TravelRequestDto dto) {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            UserEntity user = userRepository.findByUsername(username).orElseThrow();
            BranchEntity branch = branchRepository.findById(dto.branchId()).orElseThrow();
            DriverEntity driver = driverRepository.findById(UUID.fromString(dto.driverId())).orElseThrow();


            TravelEntity travel = new TravelEntity();
            travel.setTravelDate(Date.valueOf(dto.travelDate()));
            travel.setTravelDistance(dto.travelDistance());
            travel.setTravelRatePerKm(dto.travelRate());
            travel.setPaid(false);
            travel.setBranch(branch);
            travel.setDriver(driver);
            travel.setUser(user);

            TravelEntity savedTravel = travelRepository.save(travel);

            List<String> travelers = dto.travelers();

            for(String travelerId: travelers){
                TravelDetailEntity travelDetailEntity = new TravelDetailEntity();
                TravelCollaboratorKey key = new TravelCollaboratorKey(UUID.fromString(travelerId), savedTravel.getId());
                travelDetailEntity.setKey(key);
                Optional<TravelDetailEntity> savedDetail = travelDetailRepository.findByTravel_TravelDateAndCollaborator_Id(travel.getTravelDate(), UUID.fromString(travelerId));

                if(savedDetail.isPresent()){
                    throw new RuntimeException("Traveler already travel by the date selected");
                }
                travelDetailRepository.save(travelDetailEntity);
            }
            return Optional.of(savedTravel);
        }catch (Exception e){
            throw new RuntimeException("Error al registrar el viaje",e);
        }

    }

}
