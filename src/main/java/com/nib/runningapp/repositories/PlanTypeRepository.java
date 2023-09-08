package com.nib.runningapp.repositories;

import com.nib.runningapp.entities.PlanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanTypeRepository extends JpaRepository<PlanType, Long> {

    @Query("SELECT p FROM PlanType p WHERE p.status = true")
    List<PlanType> findAllActivePlanType();
}
