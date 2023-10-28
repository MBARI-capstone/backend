package com.MBARI.repository;

import com.MBARI.entity.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<ShipEntity, Integer> {
   ShipEntity findByShipName(String shipName);
}
