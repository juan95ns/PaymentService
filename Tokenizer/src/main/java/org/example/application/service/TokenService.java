package org.example.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import org.example.adapter.in.dto.CardData;
import org.example.application.port.in.TokenUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Service
public class TokenService implements TokenUseCase {

    @Value("${env.token.expiry}")
    private String TOKEN_EXPIRY_DURATION;

    @Value("${env.token.key}")
    private String TOKEN_ENCRYPTION_KEY;

    public String tokenize(CardData cardData) throws JsonProcessingException {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + Long.parseLong(TOKEN_EXPIRY_DURATION));
        String json = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(cardData);

        // The choice for JWT is made based just on personal preference, as I wanted to use it on this project.
        return Jwts.builder()
                .setSubject(json)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getTokenEncryptionKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public CardData detokenize(String token) throws JsonProcessingException {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getTokenEncryptionKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return new ObjectMapper().readValue(claims.getSubject(), CardData.class);
    }

    private Key getTokenEncryptionKey() {
        byte[] keyBytes = Decoders.BASE64.decode(TOKEN_ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
