package com.veinhorn.tikiticket.core.api;

import com.veinhorn.tikiticket.core.ResponseContext;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;

import java.io.IOException;

/**
 * Created by veinhorn on 21.12.16.
 */
public interface IAuthManager {
    ResponseContext authenticate(ICredentials creds) throws TikiTicketException;
}
