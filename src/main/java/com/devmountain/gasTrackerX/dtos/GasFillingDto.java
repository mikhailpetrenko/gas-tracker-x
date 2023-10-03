package com.devmountain.gasTrackerX.dtos;

import com.devmountain.gasTrackerX.entites.GasFilling;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GasFillingDto implements Serializable {
    private Long id;
    private Long vehicleId;
//    private Date date;
    private double fillingVolume;
    private double currentMileage;

    public GasFillingDto(GasFilling gasFilling) {
        if (gasFilling.getId() != null) {
            this.id = gasFilling.getId();
        }
        if (gasFilling.getFillingVolume() != 0) {
            this.fillingVolume = gasFilling.getFillingVolume();
        }
//        if (gasFilling.getDate() != null) {
//            this.date = gasFilling.getDate();
//        }
        if (gasFilling.getVehicleId() != null) {
            this.vehicleId = gasFilling.getVehicleId().getId();
        }
        if (gasFilling.getCurrentMileage() != 0) {
            this.currentMileage = gasFilling.getCurrentMileage();
        }

    }
}
