package com.nib.runningapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private Long id;
    private String name;
    private String description;
    private Date startTime;
    private Date endTime;
    private String location;
    private String image;
    private String condition;
}
