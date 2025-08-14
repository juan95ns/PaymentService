package org.example.application.service;

import org.example.Tokenizer;
import org.example.adapter.in.dto.CardData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Tokenizer.class)
@ActiveProfiles("test")
class TokenServiceTest {

    @Autowired
    private TokenService tokenService;

    @Test
    void testTokenService() {
        // Given
        CardData cardData = new CardData("1234567890123456", "John Doe", "12/25", "123");

        // When
        String token = tokenService.tokenize(cardData);
        CardData detokenizedData = tokenService.detokenize(token);

        // Then
        assertNotNull(token);
        assertFalse(token.isEmpty());
        assertEquals(cardData.cardHolderName(), detokenizedData.cardHolderName());
        assertEquals(cardData.cvv(), detokenizedData.cvv());
        assertEquals(cardData.expirationDate(), detokenizedData.expirationDate());
        assertEquals(cardData.pan(), detokenizedData.pan());
    }


}