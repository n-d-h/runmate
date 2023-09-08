package com.nib.runningapp.repositories;

import com.nib.runningapp.entities.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {

    @Query("SELECT ph FROM PaymentHistory ph WHERE ph.userPaymentHistory.id = :userId")
    List<PaymentHistory> getPaymentHistoryByUserId(Long userId);
}
