package com.veinhorn.tikiticket.core.context;

/**
 * Created by veinhorn on 29.12.16.
 */
public interface ContextEvent {
    ContextState getState();

    /**
     * Should return last fetched HTML page in string format
     * @return
     */
    String getLastHtml();
}
