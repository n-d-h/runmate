package com.nib.runningapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String gender;
    private String phoneNumber;
    private String imageUrl;
    private String role;
    private String progress;

    // added
    private Boolean isSubscribed;
    private UserSubscriptionDTO userActiveSubscription;
    private SubscriptionDTO activeSubscription;

    private Boolean wasSubscribed;
    private UserSubscriptionDTO userNearestPrevSubscription;
    private SubscriptionDTO prevSubscription;
}
