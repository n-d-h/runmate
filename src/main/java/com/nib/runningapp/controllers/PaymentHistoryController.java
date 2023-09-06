package com.nib.runningapp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Payment History API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment-histories")
public class PaymentHistoryController {
}
