package com.MBARI.repository;

import com.MBARI.entity.ExpeditionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpeditionRepository extends JpaRepository<ExpeditionEntity, Integer> {
    @Override
    Optional<ExpeditionEntity> findById(Integer integer);
}
