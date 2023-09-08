package com.nib.runningapp.repositories;

import com.nib.runningapp.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Query("SELECT p FROM Plan p JOIN UserPlan up " +
            "ON p.id = up.plan.id " +
            "WHERE up.planOfUser.id = :userId " +
            "AND up.status = true")
    List<Plan> findAllByUserId(Long userId);

    @Query("SELECT p FROM Plan p WHERE p.status = true")
    List<Plan> findAllActivePlan();
}
