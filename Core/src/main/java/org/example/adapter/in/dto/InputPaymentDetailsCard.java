package org.example.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InputPaymentDetailsCard extends InputPaymentDetails {
    private String token;
}
