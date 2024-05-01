package com.challenge.picpay.domain.enums;

import lombok.Getter;

@Getter
public enum ResponseStatus {

    NO_CONTENT("204", "No data found"),
    BAD_REQUEST("400", "Bad Request"),
    UNAUTHORIZED("401", "UnAuthorized");

    private final String httpReturnCode;
    private final String reason;

    private ResponseStatus(String httpReturnCode, String reason) {
        this.httpReturnCode = httpReturnCode;
        this.reason = reason;
    }

}
