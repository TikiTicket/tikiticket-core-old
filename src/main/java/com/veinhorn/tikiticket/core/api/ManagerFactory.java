package com.veinhorn.tikiticket.core.api;

import com.veinhorn.tikiticket.core.IConnector;
import com.veinhorn.tikiticket.core.account.AccountManager;
import com.veinhorn.tikiticket.core.auth.AuthManager;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;
import com.veinhorn.tikiticket.core.order.OrderManager;

/**
 * Created by veinhorn on 8.1.17.
 * Provides factory methods for common connector
 */
public class ManagerFactory {
    private static IConnector connector;

    /**
     * You should only once initialize ManagerFactory
     * @param c
     */
    public static void init(IConnector c) {
        connector = c;
    }

    public static IAuthManager newAuthManager() throws TikiTicketException {
        checkConnector("Cannot create auth manager");
        return new AuthManager(connector);
    }

    public static IOrderManager newTripManager() throws TikiTicketException {
        checkConnector("Cannot create order manager");
        return new OrderManager(connector);
    }

    public static IAccountManager newAccountManager() throws TikiTicketException {
        checkConnector("Cannot create account manager");
        return new AccountManager(connector);
    }

    private static void checkConnector(String message) throws TikiTicketException {
        if (connector == null) throw new TikiTicketException(message);
    }
}
