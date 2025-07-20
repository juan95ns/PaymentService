package org.example.adapter.in;

import org.example.adapter.in.api.TokenApi;
import org.example.adapter.in.dto.CardData;
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
    public ResponseEntity<String> createToken(CardData cardData) {
        // TODO list
        // 1. Validate the card data
        // 2. Tokenize the card data
        // 3. Response error control

        logger.debug("Creating token from card data");
        String token = tokenUseCase.tokenize(cardData);

        return ResponseEntity.ok(token);
    }

    @Override
    public ResponseEntity<CardData> resolveToken(String token) {
        // TODO list
        // 1. Validate the card data
        // 2. Detokenize the card data
        // 3. Response error control

        logger.debug("Creating card data from token");
        CardData cardData = tokenUseCase.detokenize(token);
        return ResponseEntity.ok(cardData);
    }
}
