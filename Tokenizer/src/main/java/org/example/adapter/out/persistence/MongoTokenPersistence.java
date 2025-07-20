package org.example.adapter.out.persistence;

import org.example.application.port.out.TokenRepository;

public class MongoTokenPersistence implements TokenRepository {

    @Override
    public String store(String token) {
        // TODO list
        // 1. Generate UUID
        // 2. Store the token in MongoDB with the UUID
        return "UUID";
    }

    @Override
    public String retrieve(String UUID) {
        // TODO list
        // 1. Retrieve the token from MongoDB using the UUID
        // 2. Return the token
        return "token";
    }
}
