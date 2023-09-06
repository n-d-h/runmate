package com.nib.runningapp.mappers;

import com.nib.runningapp.dtos.UserSubscriptionDTO;
import com.nib.runningapp.entities.Subscription;
import com.nib.runningapp.entities.User;
import com.nib.runningapp.entities.UserSubscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserSubscriptionMapper {

    UserSubscriptionMapper INSTANCE = Mappers.getMapper(UserSubscriptionMapper.class);

    @Mapping(target = "userId", source = "subscriptionOfUser.id")
    @Mapping(target = "username", source = "subscriptionOfUser.username")
    @Mapping(target = "subscriptionId", source = "subscription.id")
    @Mapping(target = "subscriptionType", source = "subscription.name")
    UserSubscriptionDTO toDTO(UserSubscription userSubscription);

    @Mapping(target = "subscriptionOfUser", source = "userId", qualifiedByName = "mapUser")
    @Mapping(target = "subscription", source = "subscriptionId", qualifiedByName = "mapSubscription")
    @Mapping(target = "status", ignore = true)
    UserSubscription toEntity(UserSubscriptionDTO userSubscriptionDTO);

    @Named("mapUser")
    default User mapUser(Long id) {
        User u = new User();
        u.setId(id);
        return u;
    }

    @Named("mapSubscription")
    default Subscription mapSubscription(Long id) {
        Subscription p = new Subscription();
        p.setId(id);
        return p;
    }
}
