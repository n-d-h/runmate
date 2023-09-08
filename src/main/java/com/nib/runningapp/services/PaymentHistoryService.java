package com.nib.runningapp.services;

import com.nib.runningapp.dtos.PaymentHistoryDTO;

import java.util.List;

public interface PaymentHistoryService {
    List<PaymentHistoryDTO> getPaymentHistoryByUserId(Long userId);
    PaymentHistoryDTO createPaymentHistory(PaymentHistoryDTO paymentHistoryDTO);
    PaymentHistoryDTO getPaymentHistoryById(Long paymentHistoryId);
    PaymentHistoryDTO deletePaymentHistoryById(Long paymentHistoryId);
}
