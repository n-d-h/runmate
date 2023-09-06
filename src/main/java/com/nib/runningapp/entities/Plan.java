package com.nib.runningapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "tbl_plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci", nullable = false)
    private String description;

    @Column(name = "benefits")
    private String benefits;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "plan", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<UserPlan> userPlanList;

    @ManyToOne
    @JoinColumn(name = "plan_type_id", nullable = false)
    @JsonManagedReference
    private PlanType planType;

    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    @JsonManagedReference
    private Subscription planSubscription;


}
