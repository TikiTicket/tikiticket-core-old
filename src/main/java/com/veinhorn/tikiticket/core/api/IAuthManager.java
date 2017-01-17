package com.veinhorn.tikiticket.core.api;

/**
 * Created by veinhorn on 21.12.16.
 * Provides authentication possibilities
 */
public interface IAuthManager {
    /**
     * Checks that provided credentials are valid
     * @return true if provided credentials are valid, otherwise false
     */
    boolean isValidCredentials(ICredentials creds);
}
