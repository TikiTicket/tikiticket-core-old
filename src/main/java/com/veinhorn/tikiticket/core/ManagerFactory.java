package com.veinhorn.tikiticket.core;

import com.veinhorn.tikiticket.core.account.AccountManager;
import com.veinhorn.tikiticket.core.api.IAccountManager;
import com.veinhorn.tikiticket.core.api.IOrderManager;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;
import com.veinhorn.tikiticket.core.order.OrderManager;

/**
 * Created by veinhorn on 8.1.17.
 */
public class ManagerFactory {
    private static IConnector connector;

    public static void init(IConnector c) {
        connector = c;
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
