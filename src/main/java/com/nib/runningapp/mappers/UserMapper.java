package com.nib.runningapp.mappers;

import com.nib.runningapp.dtos.UserDTO;
import com.nib.runningapp.entities.User;
import com.nib.runningapp.entities.UserSubscription;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);


    // added
//    @Mapping(target = "")
    // ---------------
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

    @AfterMapping
    default void afterMapping(@MappingTarget UserDTO userDTO, User user) {
        userDTO.setWasSubscribed(!user.getUserSubscriptionList().isEmpty());

        // get active subscription
        Optional<UserSubscription> activeSubscription = user.getUserSubscriptionList().stream()
                .filter(userSubscription -> userSubscription.getEndDate().after(new Date()))
                .findFirst();

        // get nearest subscription - if there's no active subscription
        Optional<UserSubscription> nearestSubscription = user.getUserSubscriptionList().stream()
                .filter(userSubscription -> userSubscription.getEndDate().before(new Date()))
                .max(Comparator.comparing(UserSubscription::getEndDate));


        if (activeSubscription.isPresent()) {
            // If there's active subscription, set all fields in userDTO
            UserSubscription userSubscription = activeSubscription.get();
            userDTO.setIsSubscribed(true);
            userDTO.setUserActiveSubscription(UserSubscriptionMapper.INSTANCE.toDTO(userSubscription));
            userDTO.setActiveSubscription(SubscriptionMapper.INSTANCE.toDTO(userSubscription.getSubscription()));
            if (nearestSubscription.isPresent()) {
                // If there's active subscription and nearest subscription, set all fields in userDTO
                userSubscription = nearestSubscription.get();
                userDTO.setUserNearestPrevSubscription(UserSubscriptionMapper.INSTANCE.toDTO(userSubscription));
                userDTO.setPrevSubscription(SubscriptionMapper.INSTANCE.toDTO(userSubscription.getSubscription()));
            } else {
                userDTO.setUserNearestPrevSubscription(null);
                userDTO.setPrevSubscription(null);
            }
        } else if (nearestSubscription.isPresent()) {
            // If there's no active subscription but nearest subscription, set all fields in userDTO
            UserSubscription userSubscription = nearestSubscription.get();
            userDTO.setIsSubscribed(false);
            userDTO.setUserActiveSubscription(null);
            userDTO.setActiveSubscription(null);
            userDTO.setUserNearestPrevSubscription(UserSubscriptionMapper.INSTANCE.toDTO(userSubscription));
            userDTO.setPrevSubscription(SubscriptionMapper.INSTANCE.toDTO(userSubscription.getSubscription()));
        } else {
            // If there's no active subscription and nearest subscription, set all fields in userDTO to null
            userDTO.setIsSubscribed(false);
            userDTO.setUserActiveSubscription(null);
            userDTO.setActiveSubscription(null);
            userDTO.setUserNearestPrevSubscription(null);
            userDTO.setPrevSubscription(null);
        }

    }
}
