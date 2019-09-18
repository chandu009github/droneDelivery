package com.drone.delivery.exception;



public class droneDeliveryException extends Exception {

    private final int code;

    public droneDeliveryException(Exception message, int code) {
        super(message);
        this.code = code;
    }
}
