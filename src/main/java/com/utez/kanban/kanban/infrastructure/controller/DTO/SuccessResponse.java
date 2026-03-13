package com.utez.kanban.kanban.infrastructure.controller.DTO;

public class SuccessResponse {
    private int status;
    private String message;
    private long timestamp;

    public SuccessResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
    public SuccessResponse(){}

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
