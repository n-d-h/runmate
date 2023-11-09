package com.nib.runningapp.services;

import com.nib.runningapp.dtos.UserSubscriptionCreateDTO;
import com.nib.runningapp.dtos.UserSubscriptionDTO;

public interface UserSubscriptionService {
    UserSubscriptionDTO createSubscription(UserSubscriptionCreateDTO dto);

    UserSubscriptionDTO updateSubscription(UserSubscriptionCreateDTO dto);
}
