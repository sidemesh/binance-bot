package com.sidemesh.binance.bot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.slidemesh.binance.bot.util.StringEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StringEnumTest {

    public enum TestEnum implements StringEnum<TestEnum> {

        NAME("n");
        public final String str;

        TestEnum(String str) {
            this.str = str;
        }


        @Override
        public String getStr() {
            return this.str;
        }

        @Override
        public TestEnum[] getValues() {
            return values();
        }
    }

    private static class JsonObject {
        public TestEnum te;

    }

    @Test
    public void test() throws JsonProcessingException {
        var jo = new JsonObject();
        jo.te = TestEnum.NAME;
        assertNotNull(jo.te.getStr());

        assertEquals(jo.te.jsonValue(), TestEnum.NAME.str);

        String json = "{\"te\": \"n\"}";

        var om = new ObjectMapper();
        var res = om.readValue(json, JsonObject.class);
        assertNotNull(res);
        assertEquals(res.te.jsonValue(), TestEnum.NAME.str);
    }

}
