package com.devmountain.gasTrackerX.services;

import com.devmountain.gasTrackerX.dtos.GasFillingDto;
import com.devmountain.gasTrackerX.entites.GasFilling;
import com.devmountain.gasTrackerX.entites.Vehicle;
import com.devmountain.gasTrackerX.repositories.GasFillingRepository;
import com.devmountain.gasTrackerX.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GasFillingServiceImpl implements GasFillingService {
    @Autowired
    private GasFillingRepository gasFillingRepo;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    @Transactional
    public void addGasFilling(GasFillingDto gasFillingDto, Long vehicleId) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);

        GasFilling gasFilling = new GasFilling(gasFillingDto);
        vehicleOptional.ifPresent(gasFilling::setVehicleId);
        gasFillingRepo.saveAndFlush(gasFilling);
//        if (vehicleOptional.isPresent()) {
//            GasFilling gasFilling = new GasFilling(gasFillingDto);
//            gasFilling.setVehicleId(vehicleId);
//            gasFillingRepo.saveAndFlush(gasFilling);
//        } else {
//            // Handle the case where the vehicle with the given vehicleId is not found
//        }

    }

    @Override
    public void deleteGasFillingById(Long gasFillingId) {
        Optional<GasFilling> gasFillingOptional = gasFillingRepo.findById(gasFillingId);
        gasFillingOptional.ifPresent(gasFilling -> gasFillingRepo.delete(gasFilling));
    }

    @Override
    public void deleteGasFillingsByVehicleId(Long vehicleId) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);

        if (vehicleOptional.isPresent()) {
            Vehicle vehicle = vehicleOptional.get();
            List<GasFilling> gasFillings = gasFillingRepo.findAllByVehicleId(vehicle);

            for (GasFilling gasFilling : gasFillings) {
                gasFillingRepo.delete(gasFilling);
            }
        }
    }

    @Override
    public void updateGasFillingById(GasFillingDto gasFillingDto, Long gasFillingId) {
        Optional<GasFilling> gasFillingOptional = gasFillingRepo.findById(gasFillingDto.getId());
        gasFillingOptional.ifPresent(gasFilling -> {
//            gasFilling.setDate(gasFillingDto.getDate());
            gasFilling.setFillingVolume(gasFillingDto.getFillingVolume());
            gasFilling.setCurrentMileage(gasFillingDto.getCurrentMileage());
            gasFillingRepo.saveAndFlush(gasFilling);
        });
    }

    @Override
    public Optional<GasFillingDto> getGasFillingById(Long gasFillingId) {
        return Optional.empty();
    }

    @Override
    public List<GasFillingDto> getAllGasFillingsByVehicle(Long vehicleId) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
        if (vehicleOptional.isPresent()) {
            List<GasFilling> gasFillingList = gasFillingRepo.findAllByVehicleId(vehicleOptional.get());
            return gasFillingList.stream().map(GasFillingDto::new).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
