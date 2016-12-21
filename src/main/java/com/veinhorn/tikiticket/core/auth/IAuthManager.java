package com.veinhorn.tikiticket.core.auth;

import com.veinhorn.tikiticket.core.ResponseContext;

import java.io.IOException;

/**
 * Created by veinhorn on 21.12.16.
 */
public interface IAuthManager {
    ResponseContext authenticate(ICredentials creds) throws IOException;
}
