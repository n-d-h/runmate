package com.nib.runningapp.services;

import com.nib.runningapp.dtos.SubscriptionDTO;

import java.util.List;

public interface SubscriptionService {
    SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO);
    List<SubscriptionDTO> getAllSubscriptions();
    SubscriptionDTO updateSubscription(SubscriptionDTO subscriptionDTO, Long id);
    SubscriptionDTO deleteSubscription(Long id);
}
