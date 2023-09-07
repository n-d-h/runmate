package com.nib.runningapp.services.impl;

import com.nib.runningapp.dtos.SubscriptionDTO;
import com.nib.runningapp.entities.Subscription;
import com.nib.runningapp.enums.SubscriptionType;
import com.nib.runningapp.mappers.SubscriptionMapper;
import com.nib.runningapp.repositories.SubscriptionRepository;
import com.nib.runningapp.services.SubscriptionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = SubscriptionMapper.INSTANCE.toEntity(subscriptionDTO);
        subscription.setStatus(true);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return SubscriptionMapper.INSTANCE.toDTO(savedSubscription);
    }

    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAllActiveSubscriptions();
        return subscriptions.stream().map(SubscriptionMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    @Override
    public SubscriptionDTO updateSubscription(SubscriptionDTO subscriptionDTO, Long id) {
        Subscription existingSubscription = subscriptionRepository.findById(id).orElse(null);
        if (existingSubscription != null) {
            existingSubscription.setName(SubscriptionType.valueOf(subscriptionDTO.getSubscriptionType()));
            existingSubscription.setPrice(subscriptionDTO.getPrice());
            existingSubscription.setDuration(subscriptionDTO.getDuration());
            existingSubscription.setDescription(subscriptionDTO.getDescription());
            existingSubscription.setFeature(subscriptionDTO.getFeature());

            Subscription updatedSubscription = subscriptionRepository.save(existingSubscription);
            SubscriptionDTO updateSubscriptionDTO =  SubscriptionMapper.INSTANCE.toDTO(updatedSubscription);
            updateSubscriptionDTO.setSubscriptionType(updatedSubscription.getName().toString());
            return updateSubscriptionDTO;
        }
        return null;
    }

    @Override
    public SubscriptionDTO deleteSubscription(Long id) {
        Subscription existingSubscription = subscriptionRepository.findById(id).orElse(null);
        if (existingSubscription != null) {
            existingSubscription.setStatus(false);
            Subscription deletedSubscription = subscriptionRepository.save(existingSubscription);
            return SubscriptionMapper.INSTANCE.toDTO(deletedSubscription);
        }
        return null;
    }
}
