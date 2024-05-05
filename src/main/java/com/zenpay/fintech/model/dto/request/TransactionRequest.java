package com.zenpay.fintech.model.dto.request;

import com.zenpay.fintech.model.dto.enumv.Type;
import com.zenpay.fintech.util.CustomValidator;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public abstract class TransactionRequest {
    @NotNull
    @CustomValidator(message = "Enter a valid Account Number", type = Type.NUMERIC, max = 10)
    private String accountNo;

    @NotNull
    @CustomValidator(message = "Enter a valid ISO Currency Code", type = Type.TEXT, max = 3)
    private String tranCrncy;

    @NotNull
    @CustomValidator(message = "Narration cannot contain NonAlphabetic",type = Type.TEXT)
    private String tranNarration;

    @NotNull(message = "Amount can't be Null")
    @Digits(message="Amount should contain 12 digits.", fraction = 2, integer = 12)
    @CustomValidator(message = "Amount must be minimum of {min} Naira", type = Type.SIZE, min = 1)
    private BigDecimal tranAmount;

    @NotNull
    private String tranRefNo;
}
