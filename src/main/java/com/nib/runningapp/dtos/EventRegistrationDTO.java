package com.nib.runningapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRegistrationDTO {
    private Long id;
    private Date registrationDate;
    private Long eventId;
    private String eventName;
    private Long userId;
    private String username;
}
