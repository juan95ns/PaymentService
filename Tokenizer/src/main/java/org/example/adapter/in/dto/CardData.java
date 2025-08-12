package org.example.adapter.in.dto;

/**
 * CardData represents the data of a credit or debit card.
 * It includes the card number (PAN), cardholder name, expiration date, and CVV.
 *
 * @param pan            the primary account number of the card
 * @param cardHolderName the name of the cardholder
 * @param expirationDate the expiration date of the card in MM/YY format
 * @param cvv            the card verification value
 */
public record CardData(String pan,
                       String cardHolderName,
                       String expirationDate,
                       String cvv) {
}
