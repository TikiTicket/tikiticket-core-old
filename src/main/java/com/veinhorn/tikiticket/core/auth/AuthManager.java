package com.veinhorn.tikiticket.core.auth;

import com.veinhorn.tikiticket.core.IConnector;
import com.veinhorn.tikiticket.core.ResponseContext;
import com.veinhorn.tikiticket.core.api.IAuthManager;
import com.veinhorn.tikiticket.core.api.ICredentials;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;
import com.veinhorn.tikiticket.core.util.Pair;
import com.veinhorn.tikiticket.core.util.Util;

import java.io.IOException;

import static com.veinhorn.tikiticket.core.constant.Constants.LOGIN_PAGE_URL;

/**
 * Created by veinhorn on 17.12.16.
 * IAuthManager implementation
 */
public class AuthManager implements IAuthManager {
    private IConnector connector;

    public AuthManager(IConnector connector) {
        this.connector = connector;
    }

    @Override
    public boolean isValidCredentials(ICredentials creds) {
        try {
            String authUrl = new AuthUrlParser().parse(connector.doGet(LOGIN_PAGE_URL).getHtml());
            ResponseContext ctx = connector.doPost(authUrl, Util.toPairs(creds));
            Pair locationPair = Util.findPairByKey(ctx.getHeaders(), "Location");
            // Check that Location header exists
            if (locationPair == null) return false;
            String redirectionUrl = locationPair.getValue();
            ResponseContext context = connector.doGet(redirectionUrl);
            return true;
        } catch (TikiTicketException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
