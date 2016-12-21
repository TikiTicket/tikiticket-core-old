package com.veinhorn.tikiticket.core.util;

/**
 * Created by veinhorn on 17.12.16.
 */
// TODO: Turn key/value into generic types like in Scala Tuple
public class Pair {
    private String key;
    private String value;

    public Pair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
