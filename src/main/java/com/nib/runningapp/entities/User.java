package com.nib.runningapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nib.runningapp.enums.Gender;
import com.nib.runningapp.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "progress")
    private String progress;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "userFollowing", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Follow> userFollowingList;

    @OneToMany(mappedBy = "userFollower", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Follow> userFollowerList;

    @OneToMany(mappedBy = "planOfUser", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<UserPlan> userPlanList;

    @OneToMany(mappedBy = "subscriptionOfUser", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<UserSubscription> userSubscriptionList;

    @OneToMany(mappedBy = "userPayment", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PaymentMethod> userPaymentMethodList;

    @OneToMany(mappedBy = "userPaymentHistory", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PaymentHistory> userPaymentHistoryList;

    @OneToMany(mappedBy = "userEvent", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<EventRegistration> userCourseList;

    @OneToMany(mappedBy = "userRunningSession", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<RunningSession> userRunningSessionList;
}
