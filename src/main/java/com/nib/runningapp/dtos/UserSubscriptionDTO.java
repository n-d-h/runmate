package com.nib.runningapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubscriptionDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private Long userId;
    private String username;
    private Long subscriptionId;
    private String subscriptionType;
}
