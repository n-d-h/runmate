package com.nib.runningapp.mappers;

import com.nib.runningapp.dtos.SubscriptionDTO;
import com.nib.runningapp.entities.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriptionMapper {

    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    SubscriptionDTO toDTO(Subscription subscription);

    Subscription toEntity(SubscriptionDTO subscriptionDTO);
}
