package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.javamoney.moneta.Money;

@AllArgsConstructor
@Getter
@Setter
public class Amounts {

    private Money pendingAmount;

    private Money authorizedAmount;

    private Money capturedAmount;

    private Money refundedAmount;

    private Money cancelledAmount;

}
