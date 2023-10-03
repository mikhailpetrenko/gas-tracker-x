package com.devmountain.gasTrackerX.repositories;

import com.devmountain.gasTrackerX.entites.GasFilling;
import com.devmountain.gasTrackerX.entites.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GasFillingRepository extends JpaRepository<GasFilling, Long> {
    List<GasFilling> findAllByVehicleId(Vehicle vehicleId);

}
