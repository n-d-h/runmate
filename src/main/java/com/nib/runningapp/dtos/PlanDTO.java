package com.nib.runningapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {
    private Long id;
    private String benefits;
    private String description;
    private String name;
    private Long subscriptionId;
    private String subscriptionType;
    private Long planTypeId;
    private String planType;
}
