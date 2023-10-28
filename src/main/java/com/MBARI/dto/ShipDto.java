package com.MBARI.dto;

import com.MBARI.entity.ShipEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShipDto {
    private Integer shipId;
    private String shipName;

    public static ShipDto shipEntityToShipDto(ShipEntity shipEntity) {
        ShipDto shipDto = new ShipDto();
        shipDto.setShipId(shipEntity.getShipId());
        shipDto.setShipName(shipEntity.getShipName());
        return shipDto;
    }
}
