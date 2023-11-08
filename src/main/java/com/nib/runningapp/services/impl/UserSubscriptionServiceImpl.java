package com.nib.runningapp.services.impl;

import com.nib.runningapp.dtos.UserDTO;
import com.nib.runningapp.dtos.UserSubscriptionDTO;
import com.nib.runningapp.entities.User;
import com.nib.runningapp.entities.UserSubscription;
import com.nib.runningapp.mappers.UserSubscriptionMapper;
import com.nib.runningapp.repositories.UserSubscriptionRepository;
import com.nib.runningapp.services.UserSubscriptionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class UserSubscriptionServiceImpl implements UserSubscriptionService {
    private final UserSubscriptionRepository userSubscriptionRepository;
    @Override
    public UserSubscriptionDTO createSubscription(UserSubscriptionDTO userSubscriptionDTO) {
        UserSubscription userSubscription = UserSubscriptionMapper.INSTANCE.toEntity(userSubscriptionDTO);
        userSubscription.setId(null);
        userSubscription.setStatus(true);
        return UserSubscriptionMapper.INSTANCE.toDTO(userSubscriptionRepository.save(userSubscription));
    }

    @Override
    public UserSubscriptionDTO updateSubscription(UserSubscriptionDTO userSubscriptionDTO) {
        UserSubscription userSubscription = UserSubscriptionMapper.INSTANCE.toEntity(userSubscriptionDTO);
        userSubscription.setStatus(true);
        return UserSubscriptionMapper.INSTANCE.toDTO(userSubscriptionRepository.save(userSubscription));
    }


}
