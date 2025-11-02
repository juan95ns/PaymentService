package org.example.application.service.flow;

import org.example.application.service.flow.createpayment.CreatePaymentService;
import org.example.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class FlowResolver {

    public CreatePaymentService resolveCreatePayment(Payment payment){
        // 1. Checks country configuration for the desired payment method.
        // 2. Based on the configuration, it resolves to a specific flow handler.
            // 2.5 Adds specific PaymentFlow and PaymentGateway to the Payment entity.
        // 3. Returns the appropriate CreatePaymentService implementation.
        return null;
    }
}
