package com.zenpay.fintech.model.dto.enumv;

import lombok.Getter;

@Getter
public enum AccountTypeEnum {

    SAVINGS("SBA"),OVERDRAFT("ODA"),LOAN("LAA"),FIXEDDEPOSIT("TUA"),OFFICEACCOUNT("OAB");
    private final String value;
    private AccountTypeEnum(String value) {
        this.value = value;
    }
}
