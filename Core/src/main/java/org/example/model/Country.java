package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Country {

    ES("ESP", "Spain");

    private final String ISO3Code;

    private final String name;


}
