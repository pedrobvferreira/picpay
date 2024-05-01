package com.challenge.picpay.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class CustomErrorException {

    @JsonProperty("code")
    String code;

    @JsonProperty("reason")
    String reason;

    @JsonProperty("message")
    String message;
}
