package com.veinhorn.tikiticket.core.order;

import com.veinhorn.tikiticket.core.api.IOrderDetails;

/**
 * Created by veinhorn on 30.12.16.
 */
public class OrderDetails implements IOrderDetails {
    private String orderNumber;
    private String orderDate;
    private String trainNumber;
    private String dispatchStation;
    private String destinationStation;
    private String departureDate;
    private String arrivalDate;
    private String wagonRoad;
    private String wagonOwner;
    private String wagonNumber;
    private String wagonType;
    private String numberOfSeats;
    private String seats;
    private String additionalInformation;
    private String price;
    private String orderState;
    // Electronic registration fields
    private String ecState;
    private String ecDate;

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
    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
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
    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String getWagonRoad() {
        return wagonRoad;
    }

    public void setWagonRoad(String wagonRoad) {
        this.wagonRoad = wagonRoad;
    }

    @Override
    public String getWagonOwner() {
        return wagonOwner;
    }

    public void setWagonOwner(String wagonOwner) {
        this.wagonOwner = wagonOwner;
    }

    @Override
    public String getWagonNumber() {
        return wagonNumber;
    }

    public void setWagonNumber(String wagonNumber) {
        this.wagonNumber = wagonNumber;
    }

    @Override
    public String getWagonType() {
        return wagonType;
    }

    public void setWagonType(String wagonType) {
        this.wagonType = wagonType;
    }

    @Override
    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(String numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    @Override
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    @Override
    public String getEcState() {
        return ecState;
    }

    public void setEcState(String ecState) {
        this.ecState = ecState;
    }

    @Override
    public String getEcDate() {
        return ecDate;
    }

    public void setEcDate(String ecDate) {
        this.ecDate = ecDate;
    }
}
