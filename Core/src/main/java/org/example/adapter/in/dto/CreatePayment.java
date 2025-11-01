package org.example.adapter.in.dto;

/**
 * CreatePayment represents the input data required to perform the operation to create a payment.
 *
 * @param idempotencyKey a unique key to ensure idempotent payment creation
 * @param userId         the ID of the user making the payment
 * @param orderId        the ID of the order associated with the payment
 * @param paymentMethod  the method of payment (e.g., VISA, PayPal)
 * @param currency       the currency in which the payment is made
 * @param amount         the amount involved in the payment
 * @param paymentDetails the details of the payment method
 * @param orderDetails   the details of the order
 */
public record CreatePayment(
        String idempotencyKey,
        String userId,
        String orderId,
        String Country,
        String paymentMethod,
        String currency,
        String amount,
        InputPaymentDetails paymentDetails,
        InputOrderDetails orderDetails) {
}
