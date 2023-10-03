package com.devmountain.gasTrackerX.services;


import com.devmountain.gasTrackerX.dtos.VehicleDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface VehicleService {
    List<VehicleDto> getAllVehiclesByUserId(Long userId);

//    List<VehicleDto> getAllVehicleByUserId(Long userId);

    @Transactional
    void addVehicle(VehicleDto vehicleDto, Long userId);

    @Transactional
    void deleteVehicleById(Long veihcleId);

    @Transactional
    void updateVehicleById(VehicleDto vehicleDto);

    Optional<VehicleDto> getVehicleById(Long vehicleId);
}
