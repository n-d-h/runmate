package com.nib.runningapp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nib.runningapp.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_payment_history")
public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    private User userPaymentHistory;

    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    @JsonManagedReference
    private Subscription paymentSubscription;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    @JsonManagedReference
    private PaymentMethod userPaymentMethod;
}
