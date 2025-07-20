package org.example.adapter.in.dto;

public record CardData (String pan,
                        String cardHolderName,
                        String expirationDate,
                        String cvv) {
}
