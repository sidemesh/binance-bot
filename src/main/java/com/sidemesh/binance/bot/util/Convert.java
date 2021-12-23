package com.sidemesh.binance.bot.util;

import com.google.common.collect.Maps;

import java.util.Map;

public class Convert {

    /**
     * Convert simply and not deep object only!
     */
    public static Map<String, Object> toMap(Object o) {
        Map<String, Object> map = Maps.newHashMap();

        if (o != null) {
            for (var field : o.getClass().getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    map.put(field.getName(), field.get(o));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return map;
    }

    public interface ToMap {

        default Map<String, Object> toMap() {
            return Convert.toMap(this);
        }

    }

}
