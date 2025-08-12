package org.example.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.adapter.in.dto.CardData;
import org.example.application.port.in.TokenUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService implements TokenUseCase {

    @Value("${env.token.expiry}")
    private String TOKEN_EXPIRY_DURATION;

    // TODO Using a secure key for signing the JWT. In a real application, this should be stored securely,
    //  not only for security but also to ensure that it is not hard-coded, but also to ensure on a cloud application
    //  remains the same on different instances.
    private static final Key TOKEN_ENCRYPTION_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String tokenize(CardData cardData) throws JsonProcessingException {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + Long.parseLong(TOKEN_EXPIRY_DURATION));
        String json = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(cardData);

        // The choice for JWT is made based just on personal preference, as I wanted to use it on this project.
        return Jwts.builder()
                .setSubject(json)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(TOKEN_ENCRYPTION_KEY)
                .compact();
    }

    public CardData detokenize(String token) throws JsonProcessingException {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(TOKEN_ENCRYPTION_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return new ObjectMapper().readValue(claims.getSubject(), CardData.class);
    }
}
