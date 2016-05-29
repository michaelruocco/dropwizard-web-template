package uk.co.mruoc.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage {

    @JsonProperty
    private String message;

    public ErrorMessage() {
        // Jackson deserialization
    }

    public ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
