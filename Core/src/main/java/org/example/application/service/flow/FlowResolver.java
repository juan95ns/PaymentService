package org.example.application.service.flow;

import org.example.application.service.flow.createpayment.CreatePaymentService;

public class FlowResolver {

    public CreatePaymentService resolveCreatePayment(){
        // 1. Checks country configuration for the desired payment method.
        // 2. Based on the configuration, it resolves to a specific flow handler.
        // 3. Returns the appropriate CreatePaymentService implementation.
        return null;
    }
}
