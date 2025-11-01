package org.example.application.service.flow.createpayment;


// TODO: revisit to decide if this should be an interface or abstract class depedending on future needs like shared
//  methods or properties. Probably the correct choice is abstract class to allow future shared code, but for this
//  project with only one implementation interface would be enough.

public abstract class CreatePaymentService {
    public abstract void create();
}
