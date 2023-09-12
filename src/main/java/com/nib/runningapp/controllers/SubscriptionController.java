package com.nib.runningapp.controllers;

import com.nib.runningapp.dtos.SubscriptionDTO;
import com.nib.runningapp.services.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Subscription API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subscriptions")
@SecurityRequirement(name = "Authorization")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Operation(summary = "Get all subscriptions")
    @GetMapping("")
    public ResponseEntity<?> getAllSubscriptions() {
        List<SubscriptionDTO> subscriptionDTOs = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptionDTOs);
    }

    @Operation(summary = "Create subscription")
    @PostMapping("")
    public ResponseEntity<?> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        SubscriptionDTO savedSubscriptionDTO = subscriptionService.createSubscription(subscriptionDTO);
        return ResponseEntity.created(null).body(savedSubscriptionDTO);
    }

    @Operation(summary = "Update subscription by id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubscription(@RequestBody SubscriptionDTO subscriptionDTO,
                                                @PathVariable("id") Long id) {
        SubscriptionDTO updatedSubscriptionDTO = subscriptionService.updateSubscription(subscriptionDTO, id);
        return ResponseEntity.ok(updatedSubscriptionDTO);
    }

    @Operation(summary = "Delete subscription by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable("id") Long id) {
        SubscriptionDTO deletedSubscriptionDTO = subscriptionService.deleteSubscription(id);
        return ResponseEntity.ok(deletedSubscriptionDTO);
    }

}
