package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@AllArgsConstructor
@Getter
public class PaymentDetails {

    private CardData cardData;

    private AccountData accountData;
}
