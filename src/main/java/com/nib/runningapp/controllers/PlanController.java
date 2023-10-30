package com.nib.runningapp.controllers;

import com.nib.runningapp.dtos.PlanDTO;
import com.nib.runningapp.services.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Plan API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plans")
//@SecurityRequirement(name = "Authorization")
public class PlanController {

    private final PlanService planService;

    @Operation(summary = "Get all plans")
    @GetMapping("")
    public ResponseEntity<?> getAllPlans() {
        List<PlanDTO> plans = planService.getAllPlans();
        return ResponseEntity.ok(plans);
    }

    @Operation(summary = "Get plan by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlanById(@PathVariable("id") Long id) {
        PlanDTO plan = planService.getPlanById(id);
        return ResponseEntity.ok(plan);
    }

    @Operation(summary = "Create plan")
    @PostMapping("")
    public ResponseEntity<?> createPlan(@RequestBody PlanDTO planDTO) {
        PlanDTO plan = planService.createPlan(planDTO);
        return ResponseEntity.created(null).body(plan);
    }

    @Operation(summary = "Delete plan by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlanById(@PathVariable("id") Long id) {
        PlanDTO plan = planService.deletePlanById(id);
        return ResponseEntity.ok(plan);
    }

    @Operation(summary = "Update plan by id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlan(@RequestBody PlanDTO planDTO, @PathVariable("id") Long id) {
        PlanDTO plan = planService.updatePlan(planDTO, id);
        return ResponseEntity.ok(plan);
    }
}
