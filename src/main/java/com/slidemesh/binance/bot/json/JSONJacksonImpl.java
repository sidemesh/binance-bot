package com.slidemesh.binance.bot.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONJacksonImpl implements JSON {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String toJson(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}