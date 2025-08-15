package org.example.model;

/**
 * Enum representing the different payment flow types. It can be expanded in the future to include more payment flow types.
 * <p>
 * This enum defines the types of payment flows that can be used in the system.
 * It includes:
 * - DELAYED_CAPTURE: A payment flow where the capture of funds is delayed until a later time.
 * - DIRECT_CAPTURE: A payment flow where the funds are captured immediately upon authorization.
 */
public enum PaymentFlow {
    DELAYED_CAPTURE,
    DIRECT_CAPTURE;
}
