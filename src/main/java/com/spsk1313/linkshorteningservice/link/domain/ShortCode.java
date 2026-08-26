package com.spsk1313.linkshorteningservice.link.domain;

import java.util.regex.Pattern;

public record ShortCode(String value) {

    private static final int MIN_LENGTH = 4;
    private static final int MAX_LENGTH = 32;
    private static final Pattern ALPHANUMERIC_PATTERN = Pattern.compile("[A-Za-z0-9]+");

    public ShortCode {

        if(value == null) {
            throw new IllegalArgumentException("Short code cannot be null");
        }

        if(value.isBlank()) {
            throw new IllegalArgumentException("Short code cannot be blank");
        }

        if(value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Short code must be between %d and %d characters.".formatted(MIN_LENGTH, MAX_LENGTH));
        }

        if(!ALPHANUMERIC_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Short code must be alphanumeric");
        }


    }
}
