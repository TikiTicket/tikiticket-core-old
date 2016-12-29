package com.veinhorn.tikiticket.core.context;

/**
 * Created by veinhorn on 29.12.16.
 */
public interface IContextHolder {
    void updateContext(ContextEvent event);

    /**
     * Checks authorization
     * @return
     */
    boolean isAuthorized();

    /**
     * Should return last fetched HTML page
     * @return
     */
    String getLastHtml();
}
