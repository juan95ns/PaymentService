package org.example.model;


import javax.money.CurrencyUnit;
import java.util.List;

/**
 * Represents a payment transaction. More details can be added as needed.
 */
public class Payment {

    private Id id;

    private PaymentMethod paymentMethod;

    private PaymentFlow flow;

    private PaymentGateway gateway;

    private PaymentDetails details;

    private Country country;

    private CurrencyUnit currency;

    private Amounts amounts;

    private List<Item> items;

    private List<Operation> operations;

    private Address billingAddress;

    private Address shippingAddress;

    //TODO add installments, operations?, etc.

}
