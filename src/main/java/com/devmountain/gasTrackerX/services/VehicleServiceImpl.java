package com.devmountain.gasTrackerX.services;

import com.devmountain.gasTrackerX.dtos.VehicleDto;
import com.devmountain.gasTrackerX.entites.User;
import com.devmountain.gasTrackerX.entites.Vehicle;
import com.devmountain.gasTrackerX.repositories.UserRepository;
import com.devmountain.gasTrackerX.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<VehicleDto> getAllVehiclesByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            List<Vehicle> vehicleList = vehicleRepository.findAllByUserEquals(userOptional.get());
            return vehicleList.stream().map(vehicle -> new VehicleDto(vehicle)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public void addVehicle(VehicleDto vehicleDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Vehicle vehicle = new Vehicle(vehicleDto);
        userOptional.ifPresent(vehicle::setUser);
        vehicleRepository.saveAndFlush(vehicle);
    }

    @Override
    @Transactional
    public void deleteVehicleById(Long vehicleId) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
        vehicleOptional.ifPresent(vehicle -> vehicleRepository.delete(vehicle));
    }

    @Override
    @Transactional
    public void updateVehicleById(VehicleDto vehicleDto) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleDto.getId());
        vehicleOptional.ifPresent(vehicle -> {
            vehicle.setVehicleName(vehicleDto.getVehicleName());
            vehicleRepository.saveAndFlush(vehicle);
        });
    }

    @Override
    public Optional<VehicleDto> getVehicleById(Long vehicleId) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
        return vehicleOptional.map(VehicleDto::new);
    }
}
