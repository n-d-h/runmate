package com.nib.runningapp.repositories;

import com.nib.runningapp.entities.User;
import com.nib.runningapp.entities.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {
    Optional<UserSubscription> findBySubscriptionOfUserAndEndDateGreaterThan(User user, Date date);
}
