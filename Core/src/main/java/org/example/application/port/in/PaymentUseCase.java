package org.example.application.port.in;

import org.example.adapter.in.dto.CreatePayment;
import org.example.adapter.in.dto.PaymentResponse;

public interface PaymentUseCase {

    PaymentResponse createPayment(CreatePayment createPaymentRequest);
}
