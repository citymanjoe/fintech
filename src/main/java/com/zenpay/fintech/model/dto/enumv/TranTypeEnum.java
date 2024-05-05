package com.zenpay.fintech.model.dto.enumv;

import lombok.Getter;

@Getter
public enum TranTypeEnum {

    CREDIT("C"),DEBIT("D");
    private final String value;
    private TranTypeEnum(String value) {
        this.value = value;
    }
}
