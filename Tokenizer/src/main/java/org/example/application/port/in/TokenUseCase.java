package org.example.application.port.in;

import org.example.adapter.in.dto.CardData;
import org.example.application.port.out.TokenRepository;

public interface TokenUseCase {


    String tokenize(CardData cardData);

    CardData detokenize(String token);

}