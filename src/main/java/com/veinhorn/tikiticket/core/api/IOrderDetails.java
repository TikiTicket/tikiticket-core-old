package com.veinhorn.tikiticket.core.api;

/**
 * Created by veinhorn on 2.1.17.
 */
public interface IOrderDetails {
    String getOrderNumber();
    String getOrderDate();
    String getTrainNumber();
    String getDispatchStation();
    String getDestinationStation();
    String getDepartureDate();
    String getArrivalDate();
    String getWagonRoad();
    String getWagonOwner();
    String getWagonNumber();
    String getWagonType();
    String getNumberOfSeats();
    String getSeats();
    String getAdditionalInformation();
    String getPrice();
    String getOrderState();
    String getEcState();
    String getEcDate();
}
