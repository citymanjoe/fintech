package com.zenpay.fintech.model.dto.enumv;

import lombok.Getter;

@Getter
public enum CurrencyEnum {

    NAIRA("NGN"),DOLLAR("USD"),POUND("GBP"),EURO("EUR");
    private final String value;
    private CurrencyEnum(String value) {
        this.value = value;
    }
}
