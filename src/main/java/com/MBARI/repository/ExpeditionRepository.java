package com.MBARI.repository;

import com.MBARI.entity.ExpeditionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ExpeditionRepository extends CrudRepository<ExpeditionEntity, Integer> {

}
