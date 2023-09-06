package com.nib.runningapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nib.runningapp.enums.SubscriptionType;
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
@Table(name = "tbl_subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private SubscriptionType name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "description", columnDefinition = "LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String description;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "feature")
    private String feature;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "planSubscription", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Plan> planList;

    @OneToMany(mappedBy = "courseSubscription", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Course> courseList;

    @OneToMany(mappedBy = "subscription", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<UserSubscription> userList;

    @OneToMany(mappedBy = "paymentSubscription", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PaymentHistory> subscriptionHistoryList;
}
