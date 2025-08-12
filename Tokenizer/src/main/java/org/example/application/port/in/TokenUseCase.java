package org.example.application.port.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.adapter.in.dto.CardData;

/**
 * TokenUseCase defines the contract for tokenization and detokenization operations.
 * It provides methods to convert card data into a token and to retrieve card data from a token.
 */
public interface TokenUseCase {

    String tokenize(CardData cardData) throws JsonProcessingException;

    CardData detokenize(String token) throws JsonProcessingException;

}