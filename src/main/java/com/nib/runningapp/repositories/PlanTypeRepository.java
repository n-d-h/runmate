package com.nib.runningapp.repositories;

import com.nib.runningapp.entities.PlanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanTypeRepository extends JpaRepository<PlanType, Long> {
}
