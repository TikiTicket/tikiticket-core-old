package com.veinhorn.tikiticket.core.account;

import com.veinhorn.tikiticket.core.IConnector;
import com.veinhorn.tikiticket.core.ResponseContext;
import com.veinhorn.tikiticket.core.api.IAccountManager;
import com.veinhorn.tikiticket.core.api.IPersonalData;
import com.veinhorn.tikiticket.core.auth.AuthManager;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;

import java.io.IOException;

/**
 * Created by veinhorn on 18.12.16.
 */

// TODO: Replace with proxy auth that stores last login in cache
public class AccountManager implements IAccountManager {
    private static final String PERSONAL_DATA_URL = "https://poezd.rw.by/wps/myportal/home/rp/private/private1";

    private IConnector connector;
    private AuthManager authManager;

    public AccountManager(IConnector connector) {
        this.connector = connector;
        authManager = new AuthManager(connector);
    }

    @Override
    public IPersonalData getPersonalData() throws TikiTicketException {
        try {
            ResponseContext context = authManager.authenticate(connector.getCredentials());
            String html = connector.doGet(PERSONAL_DATA_URL).getHtml();
            return new PersonalDataParser().parse(html);
        } catch (IOException e) {
            e.printStackTrace();
            throw new TikiTicketException("Cannot parse personal user data", e);
        } catch (TikiTicketException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
