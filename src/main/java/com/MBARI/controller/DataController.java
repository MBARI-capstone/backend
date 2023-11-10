package com.MBARI.controller;

import com.MBARI.dto.ShipDto;
import com.MBARI.dto.UserDto;
import com.MBARI.service.ShipService;
import com.MBARI.service.UserService;
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
    private UserService userService;

    @Autowired
    public DataController(ShipService shipService, UserService userService) {
        this.shipService = shipService;
        this.userService = userService;
    }

    @GetMapping("/allShips")
    public ResponseEntity<List<ShipDto>> getAllShips() {
        List<ShipDto> ships = shipService.getAllShips();
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }

    @GetMapping("/allRegisteredUsers")
    public ResponseEntity<List<UserDto>> getAllRegisteredUsers() {
        List<UserDto> users = userService.getAllRegisteredUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
