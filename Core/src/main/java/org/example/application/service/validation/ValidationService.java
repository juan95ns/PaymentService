package org.example.application.service.validation;

import org.example.adapter.in.dto.CreatePayment;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    /**
     * Validates the payment creation request.
     * TODO implement actual validation logic.
     * TODO the response could include validation error details instead a simple boolean.
     *
     * @param createPaymentRequest The payment request to validate.
     * @return true if the payment is valid, false otherwise.
     */
    public boolean validatePaymentCreation(CreatePayment createPaymentRequest) {
        // Implement validation logic here
        return true; // Placeholder
    }
}
