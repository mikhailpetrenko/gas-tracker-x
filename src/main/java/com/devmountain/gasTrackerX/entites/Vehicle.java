package com.devmountain.gasTrackerX.entites;

import com.devmountain.gasTrackerX.dtos.VehicleDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Vehicles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String vehicleName;

    @ManyToOne
    @JsonBackReference
    private User user;

    public Vehicle(VehicleDto vehicleDto){
        if (vehicleDto.getVehicleName() != null){
            this.vehicleName = vehicleDto.getVehicleName();
        }
    }

}
