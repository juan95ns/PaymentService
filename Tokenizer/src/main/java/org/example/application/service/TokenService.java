package org.example.application.service;

import org.example.adapter.in.dto.CardData;
import org.example.application.port.in.TokenUseCase;
import org.example.application.port.out.TokenRepository;

public class TokenService implements TokenUseCase {

    private final TokenRepository tokenRepository;

    public TokenService(final TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String tokenize(CardData cardData) {
        // TODO list
        // 1. Tokenize the card data
        // 2. Store the token in the repository with UUID
        // 3. Return the UUID

        return "UUID"; // Placeholder return value
    }

    public CardData detokenize(String token) {
        // TODO list
        // 1. Retrieve the card data from the repository using the UUID
        // 2. Decrypt the card data
        // 3. Return the card data

        return new CardData("1234-5678-9012-3456", "John Doe", "12/25", "123"); // Placeholder return value
    }
}
