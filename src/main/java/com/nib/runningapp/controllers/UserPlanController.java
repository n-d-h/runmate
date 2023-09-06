package com.nib.runningapp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Plan API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-plans")
public class UserPlanController {
}
