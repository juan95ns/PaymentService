package org.example.adapter.in.api;


import org.example.adapter.in.dto.CardData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TokenApi defines the endpoints for tokenization and resolution of card data.
 * It provides methods to create a token from card data and resolve a token to retrieve the original card data.
 */
@RequestMapping("/tokenizer")
public interface TokenApi {

    /**
     * Creates a token for the given card data.
     * @param cardData the card data to tokenize.
     * @return a ResponseEntity containing the token.
     */
    @PostMapping("/create")
    ResponseEntity<String> createToken(@RequestBody CardData cardData);

    /**
     * Resolves a token to retrieve the original card data.
     * @param token the token to resolve.
     * @return a ResponseEntity containing the original card data.
     */
    @PostMapping("/resolve")
    ResponseEntity<CardData> resolveToken(@RequestBody String token);
}
