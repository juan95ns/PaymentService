package org.example.model;

import java.util.UUID;

public record Id(UUID value, Long createdAt, String userId) {

}
