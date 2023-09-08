package com.nib.runningapp.services.impl;

import com.nib.runningapp.dtos.PlanDTO;
import com.nib.runningapp.dtos.UserPlanDTO;
import com.nib.runningapp.entities.Plan;
import com.nib.runningapp.entities.PlanType;
import com.nib.runningapp.entities.Subscription;
import com.nib.runningapp.entities.UserPlan;
import com.nib.runningapp.mappers.PlanMapper;
import com.nib.runningapp.mappers.UserPlanMapper;
import com.nib.runningapp.repositories.PlanRepository;
import com.nib.runningapp.repositories.PlanTypeRepository;
import com.nib.runningapp.repositories.SubscriptionRepository;
import com.nib.runningapp.repositories.UserPlanRepository;
import com.nib.runningapp.services.PlanService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final PlanTypeRepository planTypeRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserPlanRepository userPlanRepository;

    @Override
    public PlanDTO createPlan(PlanDTO planDTO) {
        Plan plan = PlanMapper.INSTANCE.toEntity(planDTO);
        plan.setStatus(true);
        Plan savedplan = planRepository.save(plan);
        return PlanMapper.INSTANCE.toDTO(savedplan);
    }

    @Override
    public PlanDTO getPlanById(Long id) {
        Plan plan = planRepository.findById(id).orElse(null);
        if (plan != null) {
            return PlanMapper.INSTANCE.toDTO(plan);
        }
        return null;
    }

    @Override
    public PlanDTO updatePlan(PlanDTO planDTO, Long id) {
        Plan existingPlan = planRepository.findById(id).orElse(null);
        Subscription subscription = subscriptionRepository.findById(planDTO.getSubscriptionId()).orElse(null);
        PlanType planType = planTypeRepository.findById(planDTO.getPlanTypeId()).orElse(null);
        if (existingPlan != null) {
            existingPlan.setBenefits(planDTO.getBenefits());
            existingPlan.setDescription(planDTO.getDescription());
            existingPlan.setName(planDTO.getName());
            existingPlan.setPlanSubscription(subscription);
            existingPlan.setPlanType(planType);

            Plan savedPlan = planRepository.save(existingPlan);
            return PlanMapper.INSTANCE.toDTO(savedPlan);
        }
        return null;
    }

    @Override
    public PlanDTO deletePlanById(Long id) {
        Plan plan = planRepository.findById(id).orElse(null);
        if (plan != null) {
            plan.setStatus(false);
            Plan deletePlan = planRepository.save(plan);
            return PlanMapper.INSTANCE.toDTO(deletePlan);
        }
        return null;
    }

    @Override
    public List<PlanDTO> getAllPlans() {
        List<Plan> list = planRepository.findAllActivePlan();
        if (list != null) {
            return list.stream().map(PlanMapper.INSTANCE::toDTO).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<PlanDTO> getAllPlansByUser(Long userId) {
        List<Plan> plans = planRepository.findAllByUserId(userId);
        if (plans != null) {
            return plans.stream().map(PlanMapper.INSTANCE::toDTO).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public PlanDTO applyPlanForUser(UserPlanDTO userPlanDTO) {
        UserPlan userPlan = UserPlanMapper.INSTANCE.toEntity(userPlanDTO);
        userPlan.setStatus(true);
        UserPlan savedUserPlan = userPlanRepository.save(userPlan);
        return PlanMapper.INSTANCE.toDTO(savedUserPlan.getPlan());
    }
}
