package com.veinhorn.tikiticket.core.api;

import com.veinhorn.tikiticket.core.exception.TikiTicketException;

import java.util.Date;
import java.util.List;

/**
 * Created by veinhorn on 22.12.16.
 */
public interface IOrderManager {
    /**
     * Retrieve current trips
     * @return
     * @throws TikiTicketException
     */
    List<IOrder> retrieveCurrentTrips() throws TikiTicketException;

    /**
     * Retrieve trip details. For now it's experimental and don't work
     * @return
     * @throws TikiTicketException
     */
    IOrderDetails retrieveTripDetails(IOrder order) throws TikiTicketException;

    /**
     * Retrieve completed trips from start to finish dates, or all completed trips if range
     * is not specified
     * @param start
     * @param finish
     * @return a list of completed orders
     */
    List<IOrder> retrieveCompletedTrips(Date start, Date finish) throws TikiTicketException;
}
