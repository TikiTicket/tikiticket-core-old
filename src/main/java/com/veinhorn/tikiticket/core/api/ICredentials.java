package com.veinhorn.tikiticket.core.api;

/**
 * Created by veinhorn on 18.12.16.
 * Provides credentials for authentication on web portal
 */
public interface ICredentials {
    String getLogin();
    String getPassword();
}
