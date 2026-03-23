package com.mavi.regioner.Exception;

import java.util.Date;

public class ErrorMessage {

    private final int statusCode;
    private final String message;
    private final Date timeStamp;
    private final String description;

    public ErrorMessage(String message, int statusCode, Date timeStamp, String description) {
        this.message = message;
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }
}
