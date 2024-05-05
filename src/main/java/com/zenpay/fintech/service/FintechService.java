package com.zenpay.fintech.service;

import com.zenpay.fintech.model.dto.request.AccountCreationRequest;
import com.zenpay.fintech.model.dto.request.DepositCreationRequest;
import com.zenpay.fintech.model.dto.request.WithdrawCreationRequest;
import com.zenpay.fintech.model.entity.Users;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface FintechService {
    ResponseEntity<?> postCreateAccount(AccountCreationRequest model);
    Optional<Users> getUserByEmail(String email);
    ResponseEntity<?> postCreateDeposit(DepositCreationRequest model);
    ResponseEntity<?> postCreateWithdraw(WithdrawCreationRequest model);
    ResponseEntity<?> getBalanceInquiry(String accountNo);
}
