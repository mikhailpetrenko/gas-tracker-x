package com.devmountain.gasTrackerX.services;

import com.devmountain.gasTrackerX.dtos.GasFillingDto;

import java.util.List;
import java.util.Optional;

public interface GasFillingService {
    void addGasFilling(GasFillingDto gasFillingDto, Long vehicleId);
    void deleteGasFillingById(Long gasFillingId);
    void updateGasFillingById(GasFillingDto gasFillingDto, Long gasFillingId);
    Optional<GasFillingDto> getGasFillingById(Long gasFillingId);
    List<GasFillingDto> getAllGasFillingsByVehicle(Long vehicleId);
    void deleteGasFillingsByVehicleId(Long vehicleId);

}
