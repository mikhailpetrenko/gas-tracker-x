package com.devmountain.gasTrackerX.repositories;

import com.devmountain.gasTrackerX.entites.User;
import com.devmountain.gasTrackerX.entites.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findAllByUserEquals(User user);
}
