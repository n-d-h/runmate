package com.nib.runningapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RunningSessionDTO {
    private Long id;
    private Double calories;
    private Double distance;
    private Double duration;
    private Date endTime;
    private Date startTime;
    private String route;
    private Double speed;
    private Long userId;
    private String username;
}
