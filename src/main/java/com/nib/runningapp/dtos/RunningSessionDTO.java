package com.nib.runningapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RunningSessionDTO {
    private Double distance;
    private Double duration;
    private Double calories;
    private Double speed;
    private String route;
    private Date date;
    private Date startTime;
    private Date endTime;
    private Boolean status;
    private Long userId;
    private String username;
}
