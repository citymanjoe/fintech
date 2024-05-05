package com.zenpay.fintech.model.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class WithdrawCreationRequest extends TransactionRequest {
    private String tranType = "D";
}
