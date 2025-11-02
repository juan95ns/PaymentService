package org.example.adapter.out;


import org.example.application.port.out.persistence.PaymentRepository;
import org.example.model.Id;
import org.example.model.Payment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//TODO: add database instead of in-memory storage in the future
@Component
public final class PaymentInMemoryRepository implements PaymentRepository {

    private static List<Payment> payments;

    private List<Payment> getInstance() {
        if (payments == null) {
            payments = new ArrayList<>();
        }
        return payments;
    }

    @Override
    public String save(Payment payment) {
        payment.setId(new Id(UUID.randomUUID(), System.currentTimeMillis(), "core"));
        getInstance().add(payment);
        return payment.getId().value().toString();
    }
}
