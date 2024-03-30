package com.mini.emoti.config.error;

public class UserjoinException extends Exception {
    private String errorMessage;

    public UserjoinException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
