package com.zenpay.fintech.model.dto.enumv;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
    RECORD_ALREADY_EXISTS("Record Already Exists"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    NO_RECORD_FOUND("Record with Provided Id/UserName is not Found"),
    AUTHENTICATION_FAILED("Incorrect Username and/or Password"),
    COULD_NOT_CREATE_RECORD("Could not create new record"),
    COULD_NOT_CREATE_USER("Could not create new User"),
    COULD_NOT_UPDATE_RECORD("Could not Update record"),
    COULD_NOT_DELETE_RECORD("Could not Delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email Address could not be verified");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
