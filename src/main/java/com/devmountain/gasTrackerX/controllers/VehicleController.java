package com.devmountain.gasTrackerX.controllers;

import com.devmountain.gasTrackerX.dtos.VehicleDto;
import com.devmountain.gasTrackerX.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/user/{userId}")
    public List<VehicleDto> getVehiclesByUser(@PathVariable Long userId){
        return vehicleService.getAllVehiclesByUserId(userId);
    }

    @GetMapping("/{vehicleId}")
    public Optional<VehicleDto> getVehicleById(@PathVariable Long vehicleId){
        return vehicleService.getVehicleById(vehicleId);
    }

    @PostMapping("/user/{userId}")
    public void addVehicle(@RequestBody VehicleDto vehicleDto,@PathVariable Long userId){
        vehicleService.addVehicle(vehicleDto, userId);
    }

    @DeleteMapping("/{vehicleId}")
    public void deleteVehicleById(@PathVariable Long vehicleId){
        vehicleService.deleteVehicleById(vehicleId);
    }

    @PutMapping
    public void updateNote(@RequestBody VehicleDto vehicleDto){
        vehicleService.updateVehicleById(vehicleDto);
    }
}
