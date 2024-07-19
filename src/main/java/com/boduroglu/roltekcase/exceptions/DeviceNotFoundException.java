package com.boduroglu.roltekcase.exceptions;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException() {
        super();
    }

    public DeviceNotFoundException(String message) {
        super(message);
    }
}
