package com.zenpay.fintech.model.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class DepositCreationRequest extends TransactionRequest {
    private String tranType = "C";
}
