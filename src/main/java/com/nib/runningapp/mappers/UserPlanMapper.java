package com.nib.runningapp.mappers;

import com.nib.runningapp.dtos.UserPlanDTO;
import com.nib.runningapp.entities.Plan;
import com.nib.runningapp.entities.User;
import com.nib.runningapp.entities.UserPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserPlanMapper {

    UserPlanMapper INSTANCE = Mappers.getMapper(UserPlanMapper.class);

    @Mapping(target ="userId", source = "planOfUser.id")
    @Mapping(target = "username", source = "planOfUser.username")
    @Mapping(target ="planId", source = "plan.id")
    @Mapping(target = "planName", source = "plan.name")
    UserPlanDTO toDTO(UserPlan userPlan);

    @Mapping(target = "planOfUser", source = "userId", qualifiedByName = "mapUser")
    @Mapping(target = "plan", source = "planId", qualifiedByName = "mapPlan")
    @Mapping(target = "status", ignore = true)
    UserPlan toEntity(UserPlanDTO userPlanDTO);

    @Named("mapUser")
    default User mapUser(Long id) {
        User u = new User();
        u.setId(id);
        return u;
    }

    @Named("mapPlan")
    default Plan mapPlan(Long id) {
        Plan p = new Plan();
        p.setId(id);
        return p;
    }
}
