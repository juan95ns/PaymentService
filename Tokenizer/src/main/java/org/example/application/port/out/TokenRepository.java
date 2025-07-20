package org.example.application.port.out;

public interface TokenRepository {

    String store(String token);

    String retrieve(String UUID);
}
