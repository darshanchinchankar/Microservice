package com.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @GetMapping("/{paymentId}")
    public String getPayment(@PathVariable Long paymentId) {

        return "Payment successful for paymentId: " + paymentId;
    }
}