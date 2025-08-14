package org.example.adapter.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.adapter.in.dto.CardData;
import org.example.adapter.in.dto.Token;
import org.example.adapter.in.exception.BadRequestException;
import org.example.adapter.in.exception.GeneralException;
import org.example.application.port.in.TokenUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

class TokenControllerTest {

    private TokenController tokenController;

    private TokenUseCase tokenUseCase;

    @BeforeEach
    void setUp() {
        tokenUseCase = new TokenUseCase() {
            @Override
            public String tokenize(CardData cardData) {
                // Mock implementation for testing
                return "mockToken";
            }

            @Override
            public CardData detokenize(String token) {
                // Mock implementation for testing
                return new CardData("1234567890123456", "John Doe", "12/25", "123");
            }
        };

        tokenController = new TokenController(tokenUseCase);
    }

    @Test
    void testTokenControllerCreateHappyPath() {
        // Given
        // When
        ResponseEntity<Token> response = tokenController.createToken(new CardData("1234567890123456", "John Doe", "12/25", "123"));

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("mockToken", response.getBody().value());
    }

    @Test
    void testTokenControllerCreateNullData() {
        // Given
        // When
        // Then
        Assertions.assertThrows(BadRequestException.class, () -> {
            tokenController.createToken(null);
        });
    }


    @Test
    void testTokenControllerCreateGeneralException() throws JsonProcessingException {
        // Given
        tokenUseCase = Mockito.mock(TokenUseCase.class);
        tokenController = new TokenController(tokenUseCase);
        // When
        Mockito.when(tokenUseCase.tokenize(Mockito.any(CardData.class)))
                .thenThrow(new GeneralException("General error"));
        // Then
        Assertions.assertThrows(GeneralException.class, () -> {
            tokenController.createToken(new CardData("1234567890123456", "John Doe", "12/25", "123"));
        });
    }


    @ParameterizedTest
    @MethodSource("provideCardData")
    void testTokenControllerCreateValidations(CardData input) {
        // Given
        // When
        // Then
        Assertions.assertThrows(BadRequestException.class, () -> {
            tokenController.createToken(input);
        });
    }

    @Test
    void testTokenControllerResolveHappyPath() {
        // Given
        // When
        ResponseEntity<CardData> response = tokenController.resolveToken(new Token("mockToken"));

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void testTokenControllerResolveNulls() {
        // Given
        // When
        // Then
        Assertions.assertThrows(BadRequestException.class, () -> {
            tokenController.resolveToken(null);
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            tokenController.resolveToken(new Token(null));
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            tokenController.resolveToken(new Token(""));
        });
    }

    @Test
    void testTokenControllerResolveGeneralException() throws JsonProcessingException {
        // Given
        tokenUseCase = Mockito.mock(TokenUseCase.class);
        tokenController = new TokenController(tokenUseCase);
        // When
        Mockito.when(tokenUseCase.detokenize(Mockito.any()))
                .thenThrow(new GeneralException("General error"));
        // Then
        Assertions.assertThrows(GeneralException.class, () -> {
            tokenController.resolveToken(new Token("mockToken"));
        });
    }


    private static Stream<Arguments> provideCardData() {
        return Stream.of(
                Arguments.of(new CardData(null, "John Doe", "12/25", "123")),
                Arguments.of(new CardData("9876543210987654", null, "11/24", "456")),
                Arguments.of(new CardData("9876543210987654", "John Doe", null, "123")),
                Arguments.of(new CardData("9876543210987654", "John Doe", "11/24", null)),
                Arguments.of(new CardData("9876543210987654", "John Doe", "12/21", "123")),
                Arguments.of(new CardData("9876543210987654", "John Doe", "11/24", "456333")),
                Arguments.of(new CardData("9876543210987654321321312", "John Doe", "11/24", "456dsa")),
                Arguments.of(new CardData("9876543210987654321321312", "Je", "11/24", "456dsa"))
        );
    }
}