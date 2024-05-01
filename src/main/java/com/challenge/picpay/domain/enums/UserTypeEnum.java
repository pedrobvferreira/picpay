package com.challenge.picpay.domain.enums;

import lombok.Getter;

@Getter
public enum UserTypeEnum {
    COMMON("COMMON"),
    MERCHANT("MERCHANT");

    UserTypeEnum(String value) {
        this.value = value;
    }

    private final String value;

}