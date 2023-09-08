package com.nib.runningapp.services.impl;

import com.nib.runningapp.dtos.PaymentHistoryDTO;
import com.nib.runningapp.entities.PaymentHistory;
import com.nib.runningapp.mappers.PaymentHistoryMapper;
import com.nib.runningapp.repositories.PaymentHistoryRepository;
import com.nib.runningapp.services.PaymentHistoryService;
import com.twilio.rest.api.v2010.account.call.Payment;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;

    @Override
    public List<PaymentHistoryDTO> getPaymentHistoryByUserId(Long userId) {
        List<PaymentHistory> paymentHistories = paymentHistoryRepository.getPaymentHistoryByUserId(userId);
        return paymentHistories.stream().map(PaymentHistoryMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    @Override
    public PaymentHistoryDTO createPaymentHistory(PaymentHistoryDTO paymentHistoryDTO) {
        PaymentHistory paymentHistory = PaymentHistoryMapper.INSTANCE.toEntity(paymentHistoryDTO);
        paymentHistory.setStatus(true);
        PaymentHistory savedPayment =  paymentHistoryRepository.save(paymentHistory);
        return PaymentHistoryMapper.INSTANCE.toDTO(savedPayment);
    }

    @Override
    public PaymentHistoryDTO getPaymentHistoryById(Long paymentHistoryId) {
        PaymentHistory paymentHistory = paymentHistoryRepository.findById(paymentHistoryId).orElse(null);
        if (paymentHistory != null) {
            return PaymentHistoryMapper.INSTANCE.toDTO(paymentHistory);
        }
        return null;
    }

    @Override
    public PaymentHistoryDTO deletePaymentHistoryById(Long paymentHistoryId) {
        PaymentHistory paymentHistory = paymentHistoryRepository.findById(paymentHistoryId).orElse(null);
        if (paymentHistory != null) {
            paymentHistory.setStatus(false);
            PaymentHistory deletedPaymentHistory = paymentHistoryRepository.save(paymentHistory);
            return PaymentHistoryMapper.INSTANCE.toDTO(deletedPaymentHistory);
        }
        return null;
    }
}
