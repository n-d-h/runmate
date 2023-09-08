package com.nib.runningapp.services;

import com.nib.runningapp.dtos.PlanDTO;
import com.nib.runningapp.dtos.UserPlanDTO;

import java.util.List;

public interface PlanService {
    PlanDTO createPlan(PlanDTO planDTO);
    PlanDTO getPlanById(Long id);
    PlanDTO updatePlan(PlanDTO planDTO, Long id);
    PlanDTO deletePlanById(Long id);
    List<PlanDTO> getAllPlans();
    List<PlanDTO> getAllPlansByUser(Long userId);
    PlanDTO applyPlanForUser(UserPlanDTO userPlanDTO);
}
