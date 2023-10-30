package com.nib.runningapp.controllers;

import com.nib.runningapp.dtos.PlanTypeDTO;
import com.nib.runningapp.services.PlanTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Plan Type API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plan-types")
//@SecurityRequirement(name = "Authorization")
public class PlanTypeController {

    private final PlanTypeService planTypeService;

    @Operation(summary = "Get all plan types")
    @GetMapping("")
    public ResponseEntity<?> getAllPlanTypes() {
        List<PlanTypeDTO> planTypeDTOList = planTypeService.getAllPlanTypes();
        return ResponseEntity.ok(planTypeDTOList);
    }

    @Operation(summary = "Get plan type by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlanTypeById(@PathVariable("id") Long id) {
        PlanTypeDTO planTypeDTO = planTypeService.getPlanTypeById(id);
        if (planTypeDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(planTypeDTO);
    }

    @Operation(summary = "Create plan type")
    @PostMapping("")
    public ResponseEntity<?> createPlanType(@RequestBody PlanTypeDTO planTypeDTO) {
        PlanTypeDTO savedPlanTypeDTO = planTypeService.createPlanType(planTypeDTO);
        return ResponseEntity.created(null).body(savedPlanTypeDTO);
    }

    @Operation(summary = "Update plan type by id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlanType(@PathVariable("id") Long id, @RequestBody PlanTypeDTO planTypeDTO) {
        planTypeDTO.setId(id);
        PlanTypeDTO updatedPlanTypeDTO = planTypeService.updatePlanType(planTypeDTO, id);
        if (updatedPlanTypeDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPlanTypeDTO);
    }

    @Operation(summary = "Delete plan type by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlanTypeById(@PathVariable("id") Long id) {
        PlanTypeDTO planTypeDTO = planTypeService.deletePlanTypeById(id);
        if (planTypeDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(planTypeDTO);
    }

}
