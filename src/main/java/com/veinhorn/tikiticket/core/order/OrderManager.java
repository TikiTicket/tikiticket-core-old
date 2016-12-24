package com.veinhorn.tikiticket.core.order;

import com.veinhorn.tikiticket.core.IConnector;
import com.veinhorn.tikiticket.core.ResponseContext;
import com.veinhorn.tikiticket.core.api.IOrder;
import com.veinhorn.tikiticket.core.api.IOrderManager;
import com.veinhorn.tikiticket.core.auth.AuthManager;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;

import java.io.IOException;
import java.util.List;

/**
 * Created by veinhorn on 22.12.16.
 */
public class OrderManager implements IOrderManager {
    private static final String CURRENT_ORDERS_URL = "https://poezd.rw.by/wps/myportal/home/rp/private";

    private IConnector connector;
    private AuthManager authManager;

    public OrderManager(IConnector connector) {
        this.connector = connector;
        authManager = new AuthManager(connector);
    }

    public List<IOrder> getCurrentOrders() throws TikiTicketException {
        try {
            ResponseContext context = authManager.authenticate(connector.getCredentials());
            String html = connector.doGet(CURRENT_ORDERS_URL).getHtml();
            return new CurrentOrdersParser().parse(html);
        } catch (IOException e) {
            e.printStackTrace();
            throw new TikiTicketException("Cannot parse current orders", e);
        }
    }
}
