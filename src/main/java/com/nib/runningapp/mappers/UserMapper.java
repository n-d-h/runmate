package com.nib.runningapp.mappers;

import com.nib.runningapp.dtos.UserDTO;
import com.nib.runningapp.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

    @Mapping(target = "role", source = "role")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "userFollowingList", ignore = true)
    @Mapping(target = "userFollowerList", ignore = true)
    @Mapping(target = "userPlanList", ignore = true)
    @Mapping(target = "userSubscriptionList", ignore = true)
    @Mapping(target = "userPaymentMethodList", ignore = true)
    @Mapping(target = "userPaymentHistoryList", ignore = true)
    @Mapping(target = "userCourseList", ignore = true)
    @Mapping(target = "userRunningSessionList", ignore = true)
    @Mapping(target = "status", ignore = true)
    User toEntity(UserDTO userDTO);
}
