package com.MBARI.repository;

import com.MBARI.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface shipRepository extends JpaRepository<Ship, Integer> {
   Ship findByShipName(String shipName);
}
