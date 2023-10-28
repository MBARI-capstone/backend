package com.MBARI.service;

import com.MBARI.dto.ShipDto;
import com.MBARI.entity.ShipEntity;
import com.MBARI.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipService {

    private ShipRepository shipRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public List<ShipDto> getAllShips() {
        List<ShipDto> shipDtoList = new ArrayList<>();
        for (ShipEntity ship: shipRepository.findAll()) {
            shipDtoList.add(ShipDto.shipEntityToShipDto(ship));
        }
        return shipDtoList;
    }
}
