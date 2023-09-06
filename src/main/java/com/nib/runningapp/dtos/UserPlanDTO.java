package com.nib.runningapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPlanDTO {
    private Long id;
    private Double distance;
    private Date endTime;
    private Date startTime;
    private Long userId;
    private String username;
    private Long planId;
    private String planName;
}
