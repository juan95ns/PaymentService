package org.example.adapter.in.dto;

public record InputOrderAddress(
        String street,
        String city,
        String state,
        String zipCode,
        String country
) {
}
