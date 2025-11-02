package org.example.application.port.out.persistence;

import org.example.model.Payment;

public interface PaymentRepository {

    String save(Payment payment);
}
