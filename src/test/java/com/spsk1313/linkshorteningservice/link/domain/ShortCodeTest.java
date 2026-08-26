package com.spsk1313.linkshorteningservice.link.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ShortCodeTest {

    private static final String VALID_FOUR_CHARACTER_VALUE = "Ab1d";
    private static final String VALID_THIRTY_TWO_CHARACTER_VALUE = "abcdEFGHIJKLMNOPQRSTUVWXYZ123456";

    @Test
    void shouldCreateShortCodeForValidFourCharacterValue() {
        ShortCode shortCode = new ShortCode(VALID_FOUR_CHARACTER_VALUE);

        assertEquals(VALID_FOUR_CHARACTER_VALUE, shortCode.value());
    }

    @Test
    void shouldCreateShortCodeForValidThirtyTwoCharacterValue() {
        ShortCode shortCode = new ShortCode(VALID_THIRTY_TWO_CHARACTER_VALUE);

        assertEquals(VALID_THIRTY_TWO_CHARACTER_VALUE, shortCode.value());
    }

    @Test
    void shouldPreserveOriginalCharacterCase() {
        ShortCode shortCode = new ShortCode(VALID_FOUR_CHARACTER_VALUE);

        assertEquals(VALID_FOUR_CHARACTER_VALUE, shortCode.value());
    }

    @Test
    void shouldTreatCodesWithDifferentCaseAsDifferentValues() {
        ShortCode first = new ShortCode("Ab1d");
        ShortCode second = new ShortCode("AB1D");

        assertNotEquals(first, second);
    }

    @Test
    void shouldTreatCodesWithIdenticalValuesAsEqual() {
        ShortCode first = new ShortCode("Ab1d");
        ShortCode second = new ShortCode("Ab1d");

        assertEquals(first, second);
    }

    @Test
    void shouldRejectNullValue() {
        assertThrows(IllegalArgumentException.class, () -> new ShortCode(null));
    }

    @Test
    void shouldRejectBlankValue() {
        assertThrows(IllegalArgumentException.class, () -> new ShortCode("   "));
    }

    @Test
    void shouldRejectValueShorterThanFourCharacters() {
        assertThrows(IllegalArgumentException.class, () -> new ShortCode("A1b"));
    }

    @Test
    void shouldRejectValueLongerThanThirtyTwoCharacters() {
        assertThrows(IllegalArgumentException.class, () -> new ShortCode(VALID_THIRTY_TWO_CHARACTER_VALUE + "a"));
    }

    @Test
    void shouldRejectNonAlphanumericCharacters() {
        assertThrows(IllegalArgumentException.class, () -> new ShortCode("abcd%$"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "   Ab1d",
            "Ab1d   ",
            "   Ab1d   "
    })
    void shouldRejectLeadingOrTrailingWhitespaceRatherThanTrimmingIt(String shortCode) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ShortCode(shortCode)
        );
    }
}
