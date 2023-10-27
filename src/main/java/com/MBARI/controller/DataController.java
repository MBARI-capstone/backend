package com.MBARI.controller;

import com.MBARI.entity.Ship;
import com.MBARI.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1.1/data")
public class DataController {
    private ShipService shipService;

    @Autowired
    public DataController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/allShips")
    public ResponseEntity<List<Ship>> getAllShips() {
        List<Ship> ships = shipService.getAllShips();
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }

}
