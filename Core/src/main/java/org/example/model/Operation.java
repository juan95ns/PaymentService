package org.example.model;

/**
 * Represents an operation that will be performed on a payment asynchronously. These will be stored in the database
 * and processed later, checking if there are any other operations that could be performed on the same payment.
 */
public class Operation {

    private Id id;

    private OperationType type;

    private OperationStatus status;
}
