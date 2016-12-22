package com.veinhorn.tikiticket.core.order;

import com.veinhorn.tikiticket.core.exception.TikiTicketException;

import java.util.List;

/**
 * Created by veinhorn on 22.12.16.
 */
public interface IOrderManager {
    List<IOrder> getCurrentOrders() throws TikiTicketException;
}
