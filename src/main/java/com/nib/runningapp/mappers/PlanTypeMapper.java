package com.nib.runningapp.mappers;

import com.nib.runningapp.dtos.PlanTypeDTO;
import com.nib.runningapp.entities.PlanType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlanTypeMapper {

    PlanTypeMapper INSTANCE = Mappers.getMapper(PlanTypeMapper.class);


    PlanTypeDTO toDTO(PlanType planType);

    @Mapping(target = "planList", ignore = true)
    @Mapping(target = "status", ignore = true)
    PlanType toEntity(PlanTypeDTO planTypeDTO);

}
