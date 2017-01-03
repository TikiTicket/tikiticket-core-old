package com.veinhorn.tikiticket.core.auth;

import com.veinhorn.tikiticket.core.IConnector;
import com.veinhorn.tikiticket.core.ResponseContext;
import com.veinhorn.tikiticket.core.api.IAuthManager;
import com.veinhorn.tikiticket.core.api.ICredentials;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;
import com.veinhorn.tikiticket.core.util.Util;

import java.io.IOException;

/**
 * Created by veinhorn on 17.12.16.
 */
@Deprecated
public class AuthManager implements IAuthManager {
    private static final String LOGIN_PAGE_URL = "https://poezd.rw.by/wps/portal/home/login_main";

    private IConnector connector;

    public AuthManager(IConnector connector) {
        this.connector = connector;
    }

    public ResponseContext authenticate(ICredentials creds) throws TikiTicketException {
        try {
            String authUrl = new AuthUrlParser().parse(connector.doGet(LOGIN_PAGE_URL).getHtml());

            ResponseContext context1 = connector.doPost(authUrl, Util.toPairs(creds));
            String redirectionUrl = Util.findPairByKey(context1.getHeaders(), "Location").getValue();
            return connector.doGet(redirectionUrl);
        } catch (IOException e) {
            e.printStackTrace();
            throw new TikiTicketException("Cannot make auth request", e);
        }
    }
}
