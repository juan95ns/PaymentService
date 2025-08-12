package org.example.adapter.in;

import org.example.adapter.in.api.TokenApi;
import org.example.adapter.in.dto.CardData;
import org.example.adapter.in.dto.Token;
import org.example.adapter.in.exception.BadRequestException;
import org.example.application.port.in.TokenUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class TokenController implements TokenApi {

    private static final Logger logger = LoggerFactory.getLogger(TokenController.class);

    private final TokenUseCase tokenUseCase;

    public TokenController(final TokenUseCase tokenUseCase) {
        this.tokenUseCase = tokenUseCase;
    }

    @Override
    public ResponseEntity<Token> createToken(CardData cardData) {
        if (!validateInput(cardData)) {
            logger.error("Error in card data input validation");
            throw new BadRequestException("Validation failed");
        }

        logger.debug("Creating token from card data");
        try {
            Token token = new Token(tokenUseCase.tokenize(cardData));
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            logger.error("Error creating token from card data", e);
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    public ResponseEntity<CardData> resolveToken(Token token) {
        if (token == null || token.value() == null || token.value().isEmpty()) {
            logger.error("Error in token input validation");
            throw new BadRequestException("Token must not be null or empty");
        }

        logger.debug("Creating card data from token");
        try {
            CardData cardData = tokenUseCase.detokenize(token.value());
            return ResponseEntity.ok(cardData);
        } catch (Exception e) {
            logger.error("Error creating token from card data", e);
            return ResponseEntity.badRequest().build();
        }
    }

    // TODO More validations needed for card data:
    // - Card holder name should not be empty
    // - CVV should be numeric and of valid length (3 or 4 digits)
    // - PAN should be numeric and of valid length (typically 16 digits)
    // - Expiration date should be in MM/YY format and valid
    private boolean validateInput(CardData cardData) {
        return cardData != null &&
                !cardData.cardHolderName().isEmpty() &&
                !cardData.cvv().isEmpty() &&
                !cardData.pan().isEmpty() &&
                !cardData.expirationDate().isEmpty();
    }
}
