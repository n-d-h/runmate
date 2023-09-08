package com.nib.runningapp.services;

import com.nib.runningapp.dtos.PaymentMethodDTO;

import java.util.List;

public interface PaymentMethodService {
    PaymentMethodDTO getPaymentMethodById(Long id);
    PaymentMethodDTO createPaymentMethod(PaymentMethodDTO paymentMethodDTO);
    PaymentMethodDTO updatePaymentMethod(PaymentMethodDTO paymentMethodDTO, Long id);
    PaymentMethodDTO deletePaymentMethod(Long id);
    List<PaymentMethodDTO> getAllPaymentMethodsByUserId(Long userId);
}
