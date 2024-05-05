package com.zenpay.fintech.service;

import com.zenpay.fintech.model.dto.enumv.AccountTypeEnum;
import com.zenpay.fintech.model.dto.enumv.CurrencyEnum;
import com.zenpay.fintech.model.dto.enumv.ErrorMessages;
import com.zenpay.fintech.model.dto.enumv.TranTypeEnum;
import com.zenpay.fintech.model.dto.request.AccountCreationRequest;
import com.zenpay.fintech.model.dto.request.DepositCreationRequest;
import com.zenpay.fintech.model.dto.request.WithdrawCreationRequest;
import com.zenpay.fintech.model.entity.Accounts;
import com.zenpay.fintech.model.entity.Roles;
import com.zenpay.fintech.model.entity.Transactions;
import com.zenpay.fintech.model.entity.Users;
import com.zenpay.fintech.repository.AccountRepository;
import com.zenpay.fintech.repository.RoleRepository;
import com.zenpay.fintech.repository.TransactionRepository;
import com.zenpay.fintech.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static com.zenpay.fintech.util.HelperUtils.generateRandomNumber;
import static com.zenpay.fintech.util.HelperUtils.generateRandomPassword;

@Service
@Slf4j
public class FintechServiceImpl implements FintechService {

    @Autowired
    UserRepository userRepos;

    @Autowired
    RoleRepository roleRepos;

    @Autowired
    AccountRepository AccountRepos;

    @Autowired
    TransactionRepository transactionRepos;

    @Override
    public ResponseEntity<?> postCreateAccount(AccountCreationRequest model) {
        try {
            if (this.userRepos.findByEmail(model.getEmail()).isEmpty()) {
                String accountNo = "067" + generateRandomNumber(7);
                Accounts account = new Accounts(BigDecimal.ZERO, accountNo, model.getFullName(), AccountTypeEnum.SAVINGS, new Date(), CurrencyEnum.NAIRA);
                List<Accounts> listAccount = new ArrayList<>();
                listAccount.add(account);
                String encryptPassword = generateRandomPassword();
                String password = (new BCryptPasswordEncoder(11)).encode(encryptPassword);
                Collection<Roles> roles = new ArrayList<>();
                Roles role = roleRepos.findByName("ROLE_USER").orElseThrow(() -> new NoSuchElementException("Such Role Does not exists"));
                roles.add(role);
                Users user = new Users(model.getEmail(), model.getMobileNo(), model.getDateOfBirth(), model.getFullName(), model.getAddress(), model.getBankVerificationNo(), model.getNationalIdentificationNo(), true, new Date(), password, listAccount, roles);
                userRepos.save(user);
                String successMsg = "User created successfully with password: " + encryptPassword + " and account number: " + accountNo;
                return ResponseEntity.ok().body(successMsg);
            }else {
                throw new Exception("Email already exists");
            }
        } catch (Exception ex) {
            log.info(ex.getMessage());
            String errorLog = String.format("%s: %s", new Object[] { ErrorMessages.COULD_NOT_CREATE_USER.getErrorMessage(), ex
                    .getMessage() });
            throw new Error(errorLog);
        }
    }

    @Override
    public Optional<Users> getUserByEmail(String email) {
        //return userRepos.findByEmail(email).orElseThrow(() -> new NoSuchElementException("EMAIL DOES NOT EXIST"));
        return userRepos.findByEmail(email);
    }

    @Override
    public ResponseEntity<?> postCreateDeposit(DepositCreationRequest model) {
        try {
            Users user = userRepos.findByEmail("admin@zenpay.com").orElseThrow(() -> new NoSuchElementException("ADMIN ACCOUNT DOES NOT EXISTS"));
            List<Accounts> account = user.getAccount();
            //int tranId = transactionRepos.tranCount();
            int tranId =0;
            String transactionId = String.valueOf(tranId + 1);
            Transactions credit = new Transactions(TranTypeEnum.valueOf(model.getTranType()), model.getTranAmount(), new Date(), model.getAccountNo(), true, model.getTranNarration(), transactionId, model.getTranCrncy(), model.getTranRefNo(), 2, new Date());
            Transactions debit = new Transactions(TranTypeEnum.DEBIT, model.getTranAmount().multiply(BigDecimal.valueOf(-1)), new Date(), account.get(0).getAccountNo(), true, model.getTranNarration(), transactionId, model.getTranCrncy(), model.getTranRefNo(), 1, new Date());
            List<Transactions> transact = new ArrayList<>();
            transact.add(debit);
            transact.add(credit);
            transactionRepos.saveAllAndFlush(transact);
            String successMsg = "Deposit created successfully on account number: " + model.getAccountNo() + " with transaction amount: " + model.getTranAmount();
            return ResponseEntity.ok().body(successMsg);
        } catch (Exception ex) {
            log.info(ex.getMessage());
            String errorLog = String.format("%s: %s", new Object[] { ErrorMessages.COULD_NOT_CREATE_USER.getErrorMessage(), ex
                    .getMessage() });
            throw new Error(errorLog);
        }
    }

    @Override
    public ResponseEntity<?> postCreateWithdraw(WithdrawCreationRequest model) {
        try {
            Users user = userRepos.findByEmail("admin@zenpay.com").orElseThrow(() -> new NoSuchElementException("ADMIN ACCOUNT DOES NOT EXISTS"));
            List<Accounts> account = user.getAccount();
            //int tranId = transactionRepos.tranCount();
            int tranId = 0;
            String transactionId = String.valueOf(tranId + 1);
            Transactions debit = new Transactions(TranTypeEnum.valueOf(model.getTranType()), model.getTranAmount().multiply(BigDecimal.valueOf(-1)), new Date(), model.getAccountNo(), true, model.getTranNarration(), transactionId, model.getTranCrncy(), model.getTranRefNo(), 1, new Date());
            Transactions credit = new Transactions(TranTypeEnum.CREDIT, model.getTranAmount(), new Date(), account.get(0).getAccountNo(), true, model.getTranNarration(), transactionId, model.getTranCrncy(), model.getTranRefNo(), 2, new Date());
            List<Transactions> transact = new ArrayList<>();
            transact.add(debit);
            transact.add(credit);
            transactionRepos.saveAllAndFlush(transact);
            String successMsg = "Withdraw created successfully on account number: " + model.getAccountNo() + " with transaction amount: " + model.getTranAmount();
            return ResponseEntity.ok().body(successMsg);
        } catch (Exception ex) {
            log.info(ex.getMessage());
            String errorLog = String.format("%s: %s", new Object[] { ErrorMessages.COULD_NOT_CREATE_USER.getErrorMessage(), ex
                    .getMessage() });
            throw new Error(errorLog);
        }
    }

    @Override
    public ResponseEntity<?> getBalanceInquiry(String accountNo) {
        Accounts account = AccountRepos.findByAccountNo(accountNo).orElseThrow(() -> new NoSuchElementException("ACCOUNT DOES NOT EXISTS"));
        return ResponseEntity.ok().body(account);
    }
}
