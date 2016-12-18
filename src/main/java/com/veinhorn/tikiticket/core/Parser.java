package com.veinhorn.tikiticket.core;

/**
 * Created by veinhorn on 17.12.16.
 */
public interface Parser<T> {
    T parse(String html);
}
