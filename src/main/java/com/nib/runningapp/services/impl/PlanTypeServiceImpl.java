package com.nib.runningapp.services.impl;

import com.nib.runningapp.dtos.PlanTypeDTO;
import com.nib.runningapp.entities.PlanType;
import com.nib.runningapp.mappers.PlanTypeMapper;
import com.nib.runningapp.repositories.PlanTypeRepository;
import com.nib.runningapp.services.PlanTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class PlanTypeServiceImpl implements PlanTypeService {

    private final PlanTypeRepository planTypeRepository;

    @Override
    public PlanTypeDTO createPlanType(PlanTypeDTO planTypeDTO) {
        PlanType planType = PlanTypeMapper.INSTANCE.toEntity(planTypeDTO);
        planType.setStatus(true);
        PlanType savedPlanType = planTypeRepository.save(planType);
        return PlanTypeMapper.INSTANCE.toDTO(savedPlanType);
    }

    @Override
    public PlanTypeDTO getPlanTypeById(Long id) {
        PlanType planType = planTypeRepository.findById(id).orElse(null);
        if (planType != null) {
            return PlanTypeMapper.INSTANCE.toDTO(planType);
        }
        return null;
    }

    @Override
    public PlanTypeDTO updatePlanType(PlanTypeDTO planTypeDTO, Long id) {
        PlanType existPlanType = planTypeRepository.findById(id).orElse(null);
        if (existPlanType != null) {
            existPlanType.setName(planTypeDTO.getName());
            existPlanType.setDescription(planTypeDTO.getDescription());
            existPlanType.setName(planTypeDTO.getName());

            PlanType savedPlanType = planTypeRepository.save(existPlanType);
            return PlanTypeMapper.INSTANCE.toDTO(savedPlanType);
        }
        return null;
    }

    @Override
    public PlanTypeDTO deletePlanTypeById(Long id) {
        PlanType planType = planTypeRepository.findById(id).orElse(null);
        if (planType != null) {
            planType.setStatus(false);
            PlanType savedPlanType = planTypeRepository.save(planType);
            return PlanTypeMapper.INSTANCE.toDTO(savedPlanType);
        }
        return null;
    }

    @Override
    public List<PlanTypeDTO> getAllPlanTypes() {
        List<PlanType> planTypes = planTypeRepository.findAllActivePlanType();
        return planTypes.stream().map(PlanTypeMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }
}
