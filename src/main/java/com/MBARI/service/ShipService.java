package com.MBARI.service;

import com.MBARI.entity.ShipEntity;
import com.MBARI.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService {

    private ShipRepository shipRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public List<ShipEntity> getAllShips() {
        return shipRepository.findAll();
    }
}
