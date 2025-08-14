package org.example.adapter.in;

import org.example.adapter.in.api.TokenApi;
import org.example.adapter.in.dto.CardData;
import org.example.adapter.in.dto.Token;
import org.example.adapter.in.exception.BadRequestException;
import org.example.application.port.in.TokenUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
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

        Token token = new Token(tokenUseCase.tokenize(cardData));
        return ResponseEntity.ok(token);
    }

    @Override
    public ResponseEntity<CardData> resolveToken(Token token) {
        if (token == null || token.value() == null || token.value().isEmpty()) {
            logger.error("Error in token input validation");
            throw new BadRequestException("Token must not be null or empty");
        }

        logger.debug("Creating card data from token");

        CardData cardData = tokenUseCase.detokenize(token.value());
        return ResponseEntity.ok(cardData);

    }

    private boolean validateInput(CardData cardData) {

        if (cardData == null) {
            return false;
        }

        if (cardData.cardHolderName() == null || cardData.cardHolderName().isEmpty() || cardData.cardHolderName().length() < 5) {
            return false;
        }

        if (cardData.cvv() == null || cardData.cvv().isEmpty() || !cardData.cvv().matches("\\d{3,4}")) {
            return false;
        }

        if (cardData.pan() == null || cardData.pan().isEmpty() || !cardData.pan().matches("\\d{13,19}")) {
            return false;
        }

        return cardData.expirationDate() != null && !cardData.expirationDate().isEmpty()
                && cardData.expirationDate().matches("(0[1-9]|1[0-2])/[0-9]{2}")
                && checkExpirationDate(cardData.expirationDate());
    }

    private boolean checkExpirationDate(String expirationDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
        simpleDateFormat.setLenient(false);
        Date expiry;

        try {
            expiry = simpleDateFormat.parse(expirationDate);
        } catch (ParseException e) {
            return false; // Invalid date format
        }

        return expiry.after(new Date());
    }
}
