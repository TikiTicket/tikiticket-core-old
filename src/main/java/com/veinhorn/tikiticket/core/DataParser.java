package com.veinhorn.tikiticket.core;

/**
 * Created by veinhorn on 17.12.16.
 * Base interface for all data parser implementations
 */
public interface DataParser<T> {
    T parse(String html);
}
