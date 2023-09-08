package com.nib.runningapp.services;

import com.nib.runningapp.dtos.PlanTypeDTO;

import java.util.List;

public interface PlanTypeService {
    PlanTypeDTO createPlanType(PlanTypeDTO planTypeDTO);
    PlanTypeDTO getPlanTypeById(Long id);
    PlanTypeDTO updatePlanType(PlanTypeDTO planTypeDTO, Long id);
    PlanTypeDTO deletePlanTypeById(Long id);
    List<PlanTypeDTO> getAllPlanTypes();
}
