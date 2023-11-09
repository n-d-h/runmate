package com.nib.runningapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubscriptionCreateDTO {
    private Date startDate;
    private Date endDate;
    private Long userId;
    private Long subscriptionId;
}
