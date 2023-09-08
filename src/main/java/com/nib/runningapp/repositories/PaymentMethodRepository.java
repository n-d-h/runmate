package com.nib.runningapp.repositories;

import com.nib.runningapp.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    @Query("SELECT pm FROM PaymentMethod pm WHERE pm.userPayment.id = ?1")
    List<PaymentMethod> findAllByUserPaymentId(Long userId);
}
