package com.nib.runningapp.services;

import com.nib.runningapp.dtos.UserSubscriptionDTO;

public interface UserSubscriptionService {
    UserSubscriptionDTO createSubscription(UserSubscriptionDTO userSubscriptionDTO);

    UserSubscriptionDTO updateSubscription(UserSubscriptionDTO userSubscriptionDTO);
}
