package br.com.camel.integration.credit.user.enums;

public enum StatusMessage {

    IN_PROGRESS("IN PROGRESS"),
    FAIL("FAIL"),
    FINISHED("FINISHED"),
    RETYING("RETRYING");

    private String message;

    StatusMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
