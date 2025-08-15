package org.example.adapter.in.api;

import org.example.adapter.in.dto.CreatePayment;
import org.example.adapter.in.dto.ModifyPayment;
import org.example.adapter.in.dto.PaymentResponse;
import org.example.adapter.in.dto.PaymentStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//TODO add Swagger annotations for better API documentation

/**
 * PaymentApi defines the endpoints for payment processing.
 */
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public interface PaymentApi {


    @PostMapping(value = "/create")
    ResponseEntity<PaymentResponse> createPayment(CreatePayment createPayment);

    @PostMapping(value = "/refund")
    ResponseEntity<PaymentResponse> refundPayment(ModifyPayment createPayment);

    @PostMapping(value = "/capture")
    ResponseEntity<PaymentResponse> capturePayment(ModifyPayment createPayment);

    @PostMapping(value = "/void")
    ResponseEntity<PaymentResponse> voidPayment(ModifyPayment createPayment);

    @GetMapping(value = "/status")
    ResponseEntity<PaymentStatus> getPaymentStatus(String id);
}
