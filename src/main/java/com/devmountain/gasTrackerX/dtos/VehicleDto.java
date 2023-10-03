package com.devmountain.gasTrackerX.dtos;

import com.devmountain.gasTrackerX.entites.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto implements Serializable {
    private Long id;
    private String vehicleName;
    private com.devmountain.gasTrackerX.dtos.UserDto userDto;

    public VehicleDto(Vehicle vehicle){
        if (vehicle.getId() != null){
            this.id = vehicle.getId();
        }
        if (vehicle.getVehicleName() != null){
            this.vehicleName = vehicle.getVehicleName();
        }
    }
}
