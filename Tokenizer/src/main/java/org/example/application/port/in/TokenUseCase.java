package org.example.application.port.in;

import org.example.adapter.in.dto.CardData;

/**
 * TokenUseCase defines the contract for tokenization and detokenization operations.
 * It provides methods to convert card data into a token and to retrieve card data from a token.
 */
public interface TokenUseCase {

    /**
     * Converts CardData into a token.
     *
     * @param cardData the card data to be tokenized
     * @return a string representation of the token
     * @throws org.example.adapter.in.exception.GeneralException if there is an error processing the card data into JSON
     */
    String tokenize(CardData cardData);

    /**
     * Converts a token back into CardData.
     *
     * @param token the token to be detokenized
     * @return the CardData represented by the token
     * @throws org.example.adapter.in.exception.GeneralException if there is an error processing the token
     */
    CardData detokenize(String token);

}