package org.example.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.adapter.in.dto.CardData;
import org.example.adapter.in.exception.GeneralException;
import org.example.application.port.in.TokenUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService implements TokenUseCase {

    @Value("${env.token.expiry}")
    private String TOKEN_EXPIRY_DURATION;

    @Value("${env.token.key}")
    private String TOKEN_ENCRYPTION_KEY;

    public String tokenize(CardData cardData) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + Long.parseLong(TOKEN_EXPIRY_DURATION));
        String json;

        try {
            json = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(cardData);
        } catch (Exception e) {
            throw new GeneralException("Error creating token from card data: " + e.getMessage());
        }

        // The choice for JWT is made based just on personal preference, as I wanted to use it on this project.
        return Jwts.builder()
                .setSubject(json)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getTokenEncryptionKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public CardData detokenize(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getTokenEncryptionKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        try {
            return new ObjectMapper().readValue(claims.getSubject(), CardData.class);
        } catch (Exception e) {
            throw new GeneralException("Error creating token from card data: " + e.getMessage());
        }
    }

    private Key getTokenEncryptionKey() {
        byte[] keyBytes = Decoders.BASE64.decode(TOKEN_ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
