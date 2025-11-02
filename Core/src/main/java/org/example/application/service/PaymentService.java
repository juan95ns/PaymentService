package org.example.application.service;

import org.example.adapter.in.dto.CreatePayment;
import org.example.adapter.in.dto.PaymentResponse;
import org.example.application.port.in.PaymentUseCase;
import org.example.application.port.out.persistence.PaymentRepository;
import org.example.application.service.flow.FlowResolver;
import org.example.application.service.flow.createpayment.CreatePaymentService;
import org.example.application.service.mapper.CreatePaymentMapper;
import org.example.application.service.validation.ValidationService;
import org.example.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements PaymentUseCase {

    private final ValidationService validationService;

    private final FlowResolver flowResolver;

    private final PaymentRepository paymentRepository;

    public PaymentService(final ValidationService validationService, final FlowResolver flowResolver, final PaymentRepository paymentRepository) {
        this.validationService = validationService;
        this.flowResolver = flowResolver;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentResponse createPayment(CreatePayment createPaymentRequest) {
        // 0.5 Token validations, etc.
        if (!validationService.validatePaymentCreation(createPaymentRequest)) {
            // LOG validation errors
            // TODO return proper error response
            throw new RuntimeException();
        }

        Payment payment = CreatePaymentMapper.toModel(createPaymentRequest);

        // 1. Validate flow-specific rules
        // 1.1 E.g., check country and payment method compatibility, resolving to a specific flow.
        CreatePaymentService createPaymentService = flowResolver.resolveCreatePayment(payment);


        // 2. Store payment entity as received (e.g., status = PENDING)
        // 2.1 This could trigger domain events if needed
        // 2.2 Consider if Operation Sourcing pattern is needed here
        paymentRepository.save(payment);

        // 3. Delegate on flow specific handlers (e.g., CreateDirectCapturePayment, etc.)
        createPaymentService.create(payment);

        return CreatePaymentMapper.toResponse(payment);
    }
}
