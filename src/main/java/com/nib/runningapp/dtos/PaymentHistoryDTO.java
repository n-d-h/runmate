package com.nib.runningapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistoryDTO {
    private Long id;
    private Double amount;
    private Long userId;
    private String username;
    private Date paymentDate;
    private String paymentStatus;
    private Long subscriptionId;
    private String subscriptionType;
    private Long paymentMethodId;
    private String paymentMethod;
}
