package com.nib.runningapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nib.runningapp.enums.Gender;
import com.nib.runningapp.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User implements UserDetails {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public String getPassword() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
