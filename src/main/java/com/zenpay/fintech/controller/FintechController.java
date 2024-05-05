package com.zenpay.fintech.controller;

import com.zenpay.fintech.model.dto.request.AccountCreationRequest;
import com.zenpay.fintech.model.dto.request.DepositCreationRequest;
import com.zenpay.fintech.model.dto.request.WithdrawCreationRequest;
import com.zenpay.fintech.service.FintechService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/v1"})
public class FintechController {

    @Autowired
    FintechService fintech;

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String home() {
        return "Zenpay Spring Boot Application";
    }

    @RequestMapping(value = {"/account/creation"}, method = {RequestMethod.POST})
    public ResponseEntity<?> createAccount(@RequestBody @Valid AccountCreationRequest user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed");
        }
        return fintech.postCreateAccount(user);
    }

    @RequestMapping(value = {"/transaction/deposit"}, method = {RequestMethod.POST})
    public ResponseEntity<?> createDeposit(@RequestBody @Valid DepositCreationRequest model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed");
        }
        return fintech.postCreateDeposit(model);
    }

    @RequestMapping(value = {"/transaction/withdraw"}, method = {RequestMethod.POST})
    public ResponseEntity<?> createWithdraw(@RequestBody @Valid WithdrawCreationRequest model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed");
        }
        return fintech.postCreateWithdraw(model);
    }

    @RequestMapping(value = {"/balance/inquiry/{accountNo}"}, method = {RequestMethod.GET})
    public ResponseEntity<?> balanceInquiry(@PathVariable("accountNo") String accountNo) {
        return fintech.getBalanceInquiry(accountNo);
    }
}
