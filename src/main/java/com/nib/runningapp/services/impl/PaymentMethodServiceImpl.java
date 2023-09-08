package com.nib.runningapp.services.impl;

import com.nib.runningapp.dtos.PaymentMethodDTO;
import com.nib.runningapp.entities.PaymentMethod;
import com.nib.runningapp.entities.User;
import com.nib.runningapp.enums.PaymentType;
import com.nib.runningapp.mappers.PaymentMethodMapper;
import com.nib.runningapp.repositories.PaymentMethodRepository;
import com.nib.runningapp.repositories.UserRepository;
import com.nib.runningapp.services.PaymentMethodService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final UserRepository userRepository;

    @Override
    public PaymentMethodDTO getPaymentMethodById(Long id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElse(null);
        if (paymentMethod != null) {
            return PaymentMethodMapper.INSTANCE.toDTO(paymentMethod);
        }
        return null;
    }

    @Override
    public PaymentMethodDTO createPaymentMethod(PaymentMethodDTO paymentMethodDTO) {
        PaymentMethod paymentMethod = PaymentMethodMapper.INSTANCE.toEntity(paymentMethodDTO);
        paymentMethod.setStatus(true);
        PaymentMethod savedPaymentMethod = paymentMethodRepository.save(paymentMethod);
        return PaymentMethodMapper.INSTANCE.toDTO(savedPaymentMethod);
    }

    @Override
    public PaymentMethodDTO updatePaymentMethod(PaymentMethodDTO paymentMethodDTO, Long id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElse(null);
        User user = userRepository.findById(paymentMethodDTO.getUserId()).orElse(null);
        if (paymentMethod != null) {
            paymentMethod.setIsDefault(paymentMethodDTO.getIsDefault());
            paymentMethod.setType(PaymentType.valueOf(paymentMethodDTO.getSubscriptionType()));
            paymentMethod.setUserPayment(user);

            PaymentMethod updatedPaymentMethod = paymentMethodRepository.save(paymentMethod);
            return PaymentMethodMapper.INSTANCE.toDTO(updatedPaymentMethod);
        }
        return null;
    }

    @Override
    public PaymentMethodDTO deletePaymentMethod(Long id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElse(null);
        if (paymentMethod != null) {
            paymentMethod.setStatus(false);
            PaymentMethod savedPaymentMethod = paymentMethodRepository.save(paymentMethod);
            return PaymentMethodMapper.INSTANCE.toDTO(savedPaymentMethod);
        }
        return null;
    }

    @Override
    public List<PaymentMethodDTO> getAllPaymentMethodsByUserId(Long userId) {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAllByUserPaymentId(userId);
        if (paymentMethods != null) {
            return paymentMethods.stream().map(PaymentMethodMapper.INSTANCE::toDTO).collect(Collectors.toList());
        }
        return null;
    }
}
