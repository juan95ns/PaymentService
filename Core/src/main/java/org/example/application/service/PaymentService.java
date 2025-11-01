package org.example.application.service;

import org.example.adapter.in.dto.CreatePayment;
import org.example.adapter.in.dto.PaymentResponse;
import org.example.application.port.in.PaymentUseCase;

public class PaymentService implements PaymentUseCase {

    @Override
    public PaymentResponse createPayment(CreatePayment createPaymentRequest) {
        // 0.5 Token validations, etc.

        // 1. Validate flow-specific rules
            // 1.1 E.g., check country and payment method compatibility, resolving to a specific flow.

        // 2. Store payment entity as received (e.g., status = PENDING)
            // 2.1 This could trigger domain events if needed
            // 2.2 Consider if Operation Sourcing pattern is needed here

        // 3. Delegate on flow specific handlers (e.g., CreateDirectCapturePayment, etc.)

        return null;
    }
}
