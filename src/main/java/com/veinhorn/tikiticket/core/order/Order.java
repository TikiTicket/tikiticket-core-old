package com.veinhorn.tikiticket.core.order;

import com.veinhorn.tikiticket.core.api.IOrder;

/**
 * Created by veinhorn on 23.12.16.
 */
public class Order implements IOrder {
    private String orderNumber;
    private String orderDate;
    private String tripDate;
    private String dispatchStation;
    private String destinationStation;
    private String trainNumber;
    private String numberOfSeats;
    private String price;
    private String ec;

    @Override
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    @Override
    public String getDispatchStation() {
        return dispatchStation;
    }

    public void setDispatchStation(String dispatchStation) {
        this.dispatchStation = dispatchStation;
    }

    @Override
    public String getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(String destinationStation) {
        this.destinationStation = destinationStation;
    }

    @Override
    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    @Override
    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(String numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String getEc() {
        return ec;
    }

    public void setEc(String ec) {
        this.ec = ec;
    }
}
