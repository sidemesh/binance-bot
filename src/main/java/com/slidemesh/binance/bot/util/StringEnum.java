package com.slidemesh.binance.bot.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public interface StringEnum<T extends StringEnum> {

    String getStr();

    T[] getValues();

    @JsonValue
    default String jsonValue() {
        return getStr();
    }

    @JsonCreator
    default T jsonCreator(String input) {
        final var values = getValues();
        for (var v : values) {
            if (v.getStr().equals(input)) {
                return v;
            }
        }

        throw new IllegalArgumentException("can't Deserializable input [" + input + "]");
    }

}
