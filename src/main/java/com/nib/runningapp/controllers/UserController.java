package com.nib.runningapp.controllers;

import com.nib.runningapp.dtos.*;
import com.nib.runningapp.entities.PaymentHistory;
import com.nib.runningapp.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "User API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "Authorization")
public class UserController {

    private final RunningSessionService runningSessionService;
    private final EventRegistrationService eventRegistrationService;
    private final PaymentMethodService paymentMethodService;
    private final PaymentHistoryService paymentHistoryService;
    private final FollowService followService;
    private final PlanService planService;

    // -----------------------------Running Session API For User-----------------------------

    @Operation(summary = "Get all running sessions for user")
    @GetMapping("/{userId}/running-sessions")
    public ResponseEntity<?> getRunningSessionsByUserId(@PathVariable("userId") Long userId) {
        List<RunningSessionDTO> runningSessions = runningSessionService.getAllRunningSessionsByUserId(userId);
        return ResponseEntity.ok(runningSessions);
    }

    @Operation(summary = "Create running session by user")
    @PostMapping("/{userId}/running-sessions")
    public ResponseEntity<?> createRunningSession(
            @RequestBody RunningSessionDTO runningSessionDTO, @PathVariable("userId") Long userId) {
        runningSessionDTO.setUserId(userId);
        RunningSessionDTO createdRunningSession = runningSessionService.createRunningSession(runningSessionDTO);
        return ResponseEntity.created(null).body(createdRunningSession);
    }

    @Operation(summary = "Update running session by user")
    @PutMapping("/{userId}/running-sessions/{runningSessionId}")
    public ResponseEntity<?> updateRunningSession(
            @RequestBody RunningSessionDTO runningSessionDTO, @PathVariable("userId") Long userId,
            @PathVariable("runningSessionId") Long runningSessionId) {
        runningSessionDTO.setUserId(userId);
        RunningSessionDTO updatedRunningSession = runningSessionService.updateRunningSession(runningSessionDTO, runningSessionId);
        return ResponseEntity.ok(updatedRunningSession);
    }

    @Operation(summary = "Delete running session by user")
    @DeleteMapping("/{userId}/running-sessions/{runningSessionId}")
    public ResponseEntity<?> deleteRunningSession(
            @PathVariable("userId") Long userId, @PathVariable("runningSessionId") Long runningSessionId) {
        RunningSessionDTO deletedRunningSession = runningSessionService.deleteRunningSession(runningSessionId);
        return ResponseEntity.ok(deletedRunningSession);
    }

    // -----------------------------Event Registration API For User-----------------------------

    @Operation(summary = "Register for event")
    @PostMapping("/{userId}/event-registrations/{eventId}")
    public ResponseEntity<?> registerForEvent(
            @PathVariable("userId") Long userId, @PathVariable("eventId") Long eventId) {
        EventRegistrationDTO eventRegistrationDTO = eventRegistrationService.registerForEvent(userId, eventId);
        if (eventRegistrationDTO == null) {
            return ResponseEntity.badRequest().body(new HashMap<>(
                    Map.of("message", "User already registered for this event")
            ));
        }
        return ResponseEntity.ok(eventRegistrationDTO);
    }

    @Operation(summary = "Get all events registered by user")
    @GetMapping("/{userId}/event-registrations")
    public ResponseEntity<?> getEventRegistrationsByUserId(@PathVariable("userId") Long userId) {
        List<EventDTO> eventRegistrations = eventRegistrationService.getAllEventsRegisteredByUser(userId);
        return ResponseEntity.ok(eventRegistrations);
    }

    @Operation(summary = "Cancel registration for event")
    @DeleteMapping("/{userId}/event-registrations/{eventId}")
    public ResponseEntity<?> cancelRegistration(
            @PathVariable("userId") Long userId, @PathVariable("eventId") Long eventId) {
        EventRegistrationDTO eventRegistrationDTO = eventRegistrationService.cancelRegistration(userId, eventId);
        return ResponseEntity.ok(eventRegistrationDTO);
    }

    // -----------------------------Following API For User-----------------------------

    @Operation(summary = "Follow user")
    @PostMapping("/{userId}/follow/{followingId}")
    public ResponseEntity<?> followUser(@PathVariable("userId") Long userId,
                                        @PathVariable("followingId") Long followingId) {
        FollowDTO followDTO = followService.follow(userId, followingId);
        return ResponseEntity.ok(followDTO);
    }

    @Operation(summary = "Unfollow user")
    @PostMapping("/{userId}/unfollow/{followingId}")
    public ResponseEntity<?> unFollowUser(@PathVariable("userId") Long userId,
                                          @PathVariable("followingId") Long followingId) {
        FollowDTO followDTO = followService.unfollow(userId, followingId);
        return ResponseEntity.ok(followDTO);
    }

    @Operation(summary = "Get all followers for user")
    @GetMapping("/{userId}/followers")
    public ResponseEntity<?> getAllFollowersByUserId(@PathVariable("userId") Long userId) {
        List<UserDTO> followers = followService.getAllFollowersByUserId(userId);
        return ResponseEntity.ok(followers);
    }

    @Operation(summary = "Get all followings for user")
    @GetMapping("/{userId}/followings")
    public ResponseEntity<?> getAllFollowingsByUserId(@PathVariable("userId") Long userId) {
        List<UserDTO> followings = followService.getAllFollowingsByUserId(userId);
        return ResponseEntity.ok(followings);
    }

    // -----------------------------Plan API For User-----------------------------

    @Operation(summary = "Get all plans for user")
    @GetMapping("/{userId}/plans")
    public ResponseEntity<?> getAllPlansByUserId(@PathVariable("userId") Long userId) {
        List<PlanDTO> plans = planService.getAllPlansByUser(userId);
        return ResponseEntity.ok(plans);
    }

    @Operation(summary = "Apply plan for user")
    @PostMapping("/{userId}/plans")
    public ResponseEntity<?> applyPlanForUser(@RequestBody UserPlanDTO userPlanDTO) {
        PlanDTO planDTO = planService.applyPlanForUser(userPlanDTO);
        return ResponseEntity.ok(planDTO);
    }

    // -----------------------------Payment Method For User-----------------------------

    @Operation(summary = "Get all payment methods for user")
    @GetMapping("/{userId}/payment-methods")
    public ResponseEntity<?> getAllPaymentMethodsByUserId(@PathVariable("userId") Long userId) {
        List<PaymentMethodDTO> paymentMethods = paymentMethodService.getAllPaymentMethodsByUserId(userId);
        return ResponseEntity.ok(paymentMethods);
    }

    @Operation(summary = "Create payment method for user")
    @PostMapping("/{userId}/payment-methods")
    public ResponseEntity<?> createPaymentMethodForUser(
            @RequestBody PaymentMethodDTO paymentMethodDTO, @PathVariable("userId") Long userId) {
        paymentMethodDTO.setUserId(userId);
        PaymentMethodDTO createdPaymentMethod = paymentMethodService.createPaymentMethod(paymentMethodDTO);
        return ResponseEntity.created(null).body(createdPaymentMethod);
    }

    @Operation(summary = "Update payment method for user")
    @PutMapping("/{userId}/payment-methods/{paymentMethodId}")
    public ResponseEntity<?> updatePaymentMethodForUser(
            @RequestBody PaymentMethodDTO paymentMethodDTO, @PathVariable("userId") Long userId,
            @PathVariable("paymentMethodId") Long paymentMethodId) {
        paymentMethodDTO.setUserId(userId);
        PaymentMethodDTO updatedPaymentMethod = paymentMethodService.updatePaymentMethod(paymentMethodDTO, paymentMethodId);
        return ResponseEntity.ok(updatedPaymentMethod);
    }

    @Operation(summary = "Delete payment method for user")
    @DeleteMapping("/{userId}/payment-methods/{paymentMethodId}")
    public ResponseEntity<?> deletePaymentMethodForUser(
            @PathVariable("userId") Long userId, @PathVariable("paymentMethodId") Long paymentMethodId) {
        PaymentMethodDTO deletedPaymentMethod = paymentMethodService.deletePaymentMethod(paymentMethodId);
        return ResponseEntity.ok(deletedPaymentMethod);
    }

    // -----------------------------Payment History API For Admin-----------------------------

    @Operation(summary = "Get all payment histories for user")
    @GetMapping("/{userId}/payment-histories")
    public ResponseEntity<?> getAllPaymentHistoryByUserId(@PathVariable("userId") Long userId) {
        List<PaymentHistoryDTO> paymentHistories = paymentHistoryService.getPaymentHistoryByUserId(userId);
        return ResponseEntity.ok(paymentHistories);
    }

    @Operation(summary = "Create payment history for user")
    @PostMapping("/{userId}/payment-histories")
    public ResponseEntity<?> createPaymentHistoryForUser(
            @RequestBody PaymentHistoryDTO paymentHistoryDTO, @PathVariable("userId") Long userId) {
        paymentHistoryDTO.setUserId(userId);
        PaymentHistoryDTO createdPaymentHistory = paymentHistoryService.createPaymentHistory(paymentHistoryDTO);
        return ResponseEntity.created(null).body(createdPaymentHistory);
    }

    @Operation(summary = "Delete payment history for user")
    @DeleteMapping("/{userId}/payment-histories/{paymentHistoryId}")
    public ResponseEntity<?> deletePaymentHistoryForUser(
            @PathVariable("userId") Long userId, @PathVariable("paymentHistoryId") Long paymentHistoryId) {
        PaymentHistoryDTO deletedPaymentHistory = paymentHistoryService.deletePaymentHistoryById(paymentHistoryId);
        return ResponseEntity.ok(deletedPaymentHistory);
    }

    @Operation(summary = "Get payment history by id")
    @GetMapping("/{userId}/payment-histories/{paymentHistoryId}")
    public ResponseEntity<?> getPaymentHistoryById(
            @PathVariable("userId") Long userId, @PathVariable("paymentHistoryId") Long paymentHistoryId) {
        PaymentHistoryDTO paymentHistoryDTO = paymentHistoryService.getPaymentHistoryById(paymentHistoryId);
        return ResponseEntity.ok(paymentHistoryDTO);
    }

}
