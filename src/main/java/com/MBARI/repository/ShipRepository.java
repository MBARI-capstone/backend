package com.MBARI.repository;

import com.MBARI.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Integer> {
   Ship findByShipName(String shipName);
}
