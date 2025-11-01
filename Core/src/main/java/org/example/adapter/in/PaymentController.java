package org.example.adapter.in;

import org.example.adapter.in.api.PaymentApi;
import org.example.adapter.in.dto.CreatePayment;
import org.example.adapter.in.dto.ModifyPayment;
import org.example.adapter.in.dto.PaymentResponse;
import org.example.adapter.in.dto.PaymentStatus;
import org.springframework.http.ResponseEntity;

public class PaymentController implements PaymentApi {
    @Override
    public ResponseEntity<PaymentResponse> createPayment(CreatePayment createPayment) {
        // 1. Validate basic input

        // 2. Send to application layer (CreatePaymentUseCase)

        // 3. Return response

        return null;
    }

    @Override
    public ResponseEntity<PaymentResponse> refundPayment(ModifyPayment createPayment) {
        return null;
    }

    @Override
    public ResponseEntity<PaymentResponse> capturePayment(ModifyPayment createPayment) {
        return null;
    }

    @Override
    public ResponseEntity<PaymentResponse> voidPayment(ModifyPayment createPayment) {
        return null;
    }

    @Override
    public ResponseEntity<PaymentStatus> getPaymentStatus(String id) {
        return null;
    }
}
