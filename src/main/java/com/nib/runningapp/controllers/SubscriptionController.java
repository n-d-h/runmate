package com.nib.runningapp.controllers;

import com.nib.runningapp.dtos.SubscriptionDTO;
import com.nib.runningapp.services.SubscriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Subscription API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("")
    public ResponseEntity<?> getAllSubscriptions() {
        List<SubscriptionDTO> subscriptionDTOs = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptionDTOs);
    }

    @PostMapping("")
    public ResponseEntity<?> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        SubscriptionDTO savedSubscriptionDTO = subscriptionService.createSubscription(subscriptionDTO);
        return ResponseEntity.created(null).body(savedSubscriptionDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubscription(@RequestBody SubscriptionDTO subscriptionDTO,
                                                @PathVariable("id") Long id) {
        SubscriptionDTO updatedSubscriptionDTO = subscriptionService.updateSubscription(subscriptionDTO, id);
        return ResponseEntity.ok(updatedSubscriptionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable("id") Long id) {
        SubscriptionDTO deletedSubscriptionDTO = subscriptionService.deleteSubscription(id);
        return ResponseEntity.ok(deletedSubscriptionDTO);
    }
}
