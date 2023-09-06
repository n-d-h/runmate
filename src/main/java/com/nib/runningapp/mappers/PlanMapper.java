package com.nib.runningapp.mappers;

import com.nib.runningapp.dtos.PlanDTO;
import com.nib.runningapp.entities.Plan;
import com.nib.runningapp.entities.PlanType;
import com.nib.runningapp.entities.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlanMapper {

    PlanMapper INSTANCE = Mappers.getMapper(PlanMapper.class);

    @Mapping(target = "subscriptionId", source = "planSubscription.id")
    @Mapping(target = "subscriptionType", source = "planSubscription.name")
    @Mapping(target = "planTypeId", source = "planType.id")
    @Mapping(target = "planType", source = "planType.name")
    PlanDTO toDTO(Plan plan);

    @Mapping(target = "planSubscription", source = "subscriptionId", qualifiedByName = "mapSubscription")
    @Mapping(target = "planType", source = "planTypeId", qualifiedByName = "mapPlanType")
    @Mapping(target = "status", ignore = true)
    Plan toEntity(PlanDTO planDTO);

    @Named("mapSubscription")
    default Subscription mapSubscription(Long id) {
        Subscription s = new Subscription();
        s.setId(id);
        return s;
    }

    @Named("mapPlanType")
    default PlanType mapPlanType(Long id) {
        PlanType p = new PlanType();
        p.setId(id);
        return p;
    }
}
