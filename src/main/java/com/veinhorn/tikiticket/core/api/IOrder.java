package com.veinhorn.tikiticket.core.api;

/**
 * Created by veinhorn on 23.12.16.
 */
public interface IOrder {
    String getOrderNumber();
    String getOrderDate();
    String getTripDate();
    String getDispatchStation();
    String getDestinationStation();
    String getTrainNumber();
    String getNumberOfSeats();
    String getPrice();
    String getEc();
}
